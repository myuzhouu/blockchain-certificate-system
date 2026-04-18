
package com.code.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.grpc.ChannelCredentials;
import io.grpc.Grpc;
import io.grpc.ManagedChannel;
import io.grpc.TlsChannelCredentials;
import org.hyperledger.fabric.client.*;
import org.hyperledger.fabric.client.identity.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class MyBlockchainUtils {
    //区块链配置文件路径 项目存放路径
    // public static String basePath = System.getProperty("user.dir")+"\\";
    public static String basePath = "E:\\定做代码10001\\2518-基于区块链的电子投票系统\\myproject_fabric_vote\\";
    public static String IPAddresss = "192.168.13.130:7051";
    public static String dataFlag = "zdd_2588_";

    private static class ConnFabric {
        private ManagedChannel grpcChannel;
        private Gateway.Builder builder;
        private MyBlockchainUtils aa;


        public ManagedChannel getGrpcChannel() {
            return grpcChannel;
        }

        public Gateway.Builder getBuilder() {
            return builder;
        }

        public MyBlockchainUtils getAa() {
            return aa;
        }

        public ConnFabric invoke() throws IOException, CertificateException, InvalidKeyException {
            // Create client identity based on X.509 certificate.
            Reader certReader = Files.newBufferedReader(Paths.get(basePath + "src/main/resources/org1.example.com/users/User1@org1.example.com/msp/signcerts/User1@org1.example.com-cert.pem"));
            X509Certificate certificate = Identities.readX509Certificate(certReader);
            Identity identity = new X509Identity("Org1MSP", certificate);

            // Create signing implementation based on private key.
            Reader keyReader = Files.newBufferedReader(Paths.get(basePath + "src/main/resources/org1.example.com/users/User1@org1.example.com/msp/keystore/priv_sk"));
            PrivateKey privateKey = Identities.readPrivateKey(keyReader);
            Signer signer = Signers.newPrivateKeySigner(privateKey);

            // Create gRPC client connection, which should be shared by all gateway connections to this endpoint.
            ChannelCredentials tlsCredentials = TlsChannelCredentials.newBuilder()
                    .trustManager(Paths.get(basePath + "src/main/resources/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt").toFile())
                    .build();
            grpcChannel = Grpc.newChannelBuilder(IPAddresss, tlsCredentials)
                    .overrideAuthority("peer0.org1.example.com")
                    .build();
            // Create a Gateway connection for a specific client identity.
            builder = Gateway.newInstance()
                    .identity(identity)
                    .signer(signer)
                    //tiangming .hash(Hash.SHA256)
                    .connection(grpcChannel);
            aa = new MyBlockchainUtils();
            return this;
        }
    }

    public static void submitData(String flag, String data) {
        ConnFabric connFabric = null;
        try {
            connFabric = new ConnFabric().invoke();

            ManagedChannel grpcChannel = connFabric.getGrpcChannel();
            Gateway.Builder builder = connFabric.getBuilder();
            MyBlockchainUtils aa = connFabric.getAa();
            try (Gateway gateway = builder.connect()) {
                // Obtain smart contract deployed on the network.
                Network network = gateway.getNetwork("mychannel");
                Contract contract = network.getContract("basic");
                String result = contract.getChaincodeName();
                System.out.println("Hyperledger Fabric  The connection is established successfully. ");
                aa.run(contract, flag, data);
            } finally {
                grpcChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<MyBlockchainObject> queryData(String flag) {
        List<MyBlockchainObject> dataList = new ArrayList<MyBlockchainObject>();
        try {
            ConnFabric connFabric = new ConnFabric().invoke();
            ManagedChannel grpcChannel = connFabric.getGrpcChannel();
            Gateway.Builder builder = connFabric.getBuilder();
            MyBlockchainUtils aa = connFabric.getAa();

            try (Gateway gateway = builder.connect()) {
                // Obtain smart contract deployed on the network.
                Network network = gateway.getNetwork("mychannel");
                Contract contract = network.getContract("basic");
                String result = contract.getChaincodeName();
                dataList = aa.run(contract, flag);
            } finally {
                grpcChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public void run(Contract contract, String flag, String data) throws GatewayException, CommitException {
        System.out.println("\n--> Evaluate Transaction: write, function returns all the current assets on the ledger");
        try {
            //异常代码  {"Owner":"Jin Soo","Size":10,"Color":"green","AppraisedValue":500,"ID":"asset3"}
            // 第一个字段不能重复
            String ids = flag + "_" + System.currentTimeMillis();
            byte[] result = contract.submitTransaction("CreateAsset", ids, "8", "9", data, "22");
            System.out.println("*** Result: " + prettyJson(result));

        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<MyBlockchainObject> run(Contract contract, String flag) throws GatewayException, CommitException {
        System.out.println("\n--> Evaluate Transaction: GetAllAssets, function returns all the current assets on the ledger");
        byte[] result = contract.evaluateTransaction("GetAllAssets");
        List<MyBlockchainObject> dataList = new ArrayList<MyBlockchainObject>();
        //System.out.println("*** Result: " + prettyJson(result));
        JSONArray objects = JSON.parseArray(prettyJson(result));
        for (Object obj : objects) {
            JSONObject obj2 = (JSONObject) obj;
            //System.out.println("obj = " + obj);
            //System.out.println("obj = " +obj2.getString("Owner"));
            String ID = obj2.getString("ID");
            String Owner = obj2.getString("Owner");
            if (ID.startsWith(flag)) {
                MyBlockchainObject bean = JSON.parseObject(Owner, MyBlockchainObject.class);
                System.out.println("bean = " + bean);
                dataList.add(bean);
            }
        }
        return dataList;
    }


    private String prettyJson(final byte[] json) {
        return prettyJson(new String(json, StandardCharsets.UTF_8));
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private String prettyJson(final String json) {
        JsonElement parsedJson = JsonParser.parseString(json);

        return gson.toJson(parsedJson);
    }


    public static void main(String[] args) throws Exception {
        String currentWorkingDirectory = System.getProperty("user.dir");
        System.out.println("当前项目路径：" + currentWorkingDirectory);
        submitData("test", "{\"data\":\"AAAA\",\"id\":1,\"name\":\"张三\",\"times\":\"2025\"}");
        List<MyBlockchainObject> dataList = queryData("test");
        for (MyBlockchainObject obj : dataList) {
            System.out.println("obj = " + obj);
        }
    }

    @Override
    public String toString() {
        return "FabricBean{" +
                "gson=" + gson +
                '}';
    }
}
