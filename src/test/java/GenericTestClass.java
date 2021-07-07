import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.model.*;
import edu.mum.cs.bankingapp.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.spec.KeySpec;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class GenericTestClass {
    Account account = null;
    User user = null;
    MongoClient mongo = null;

    @Before
    public void SetUp() {//5b4aab8bf9d39fd2df34255a
        account = new Account("5b4a2cf29a13a4724a86e2a4", "5b4786d5f9d37ce4e6823187", 43355252, "Checking",
                6700, "Active");
        user = new User("5b4786d5f9d37ce4e6823187", "Oluwajoba", "joba", "joba123", "joba@mum.edu",
                "+16415050725", new Address("5b4786d5f9d37ce4e6823185", "NORTH 4TH STR", "Fairfield", "Iowa", "USA", "52557"));

        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

//    @Test
    public void userTest() {
        UserService service = new UserService(mongo);
//        User user122 = service.createUser(this.user);
        User user = service.retrieveUserById("5b4786d5f9d37ce4e6823187");
        User user1 = service.retrieveUserByEmail("joba@mum.edu");
        User user2 = service.retrieveUserByMobile("+16415050725");
        User user3 = service.retrieveUserByUsername("joba");
        List<User> users = service.retrieveAllUser();
        Assert.assertNotNull(user);
    }


    //    @Test
    public void createAccountTest() {
//        AccountService service = new AccountService(mongo);
//        Account account = service.createAccount(this.account);
//        Account lastAccount = service.findLastAccount();
//        Account accountByAccountId = service.findAccountByAccountId("5b4a2bf69a13e5cba0adb937");
//        Account accountByNumber = service.findAccountByNumber(43355251);
//        Account accountByUserId = service.findAccountByUserId("5b4786d5f9d37ce4e6823187");
//        Assert.assertNotNull(accountByAccountId);
    }

    //    @Test
    public void testUpdateAccount() {
//        AccountService service = new AccountService(mongo);
//        service.updateAccount(account);
//        Account accountt = service.findAccountByNumber(43355252);
//        Assert.assertNotNull(accountt);
    }

//        @Test
    public void testCreateTransfer() {
        TransferService service = new TransferService(mongo);
        Transfer transfer = new Transfer("", 43355252, "Chrisner", LocalDate.now(), "Transfering for Segunn"
                , 100.0);
        Response doTransfer = service.doTransfer(transfer, user);
        Assert.assertNotNull(doTransfer);
    }

//    @Test
    public void testHashPassword() throws Exception{
        KeySpec spec = new PBEKeySpec("joba123".toCharArray(), "saltvalue".getBytes(), 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = f.generateSecret(spec).getEncoded();
        Base64.Encoder enc = Base64.getEncoder();
        System.out.printf("hash: %s%n", enc.encodeToString(hash));
    }

//    @Test
    public void testFetchTransactionHistory() {
        TransactionHistoryService service = new TransactionHistoryService(mongo);
        List<TransactionHistoryResponse> history = service.findAllHistory(user);
        history.stream().forEach(System.out::println);
    }

//        @Test
    public void testCreateBillPaymentItem() {
        BillPaymentService service = new BillPaymentService(mongo);
        List<BillPayment> billPayments = Arrays.asList(
                new BillPayment(null, "Cable TV", "Payment for Cable TV", 55, "Active"),
                new BillPayment(null, "High Speed Internet", "Payment for High Speed Internet", 30, "Active"),
                new BillPayment(null, "Mobile Phone", "Payment for Mobile Phone", 45, "Active"),
                new BillPayment(null, "Rent", "Payment for Rent", 100, "Active"),
                new BillPayment(null, "Car Insurance", "Payment for Car Insurance", 250, "Active"),
                new BillPayment(null, "Electricity", "Payment for Electricity", 25, "Active"),
                new BillPayment(null, "Water Bill", "Payment for Water Bill", 60, "Active"),
                new BillPayment(null, "Trash Bill", "Payment for Trash Bill", 51, "Active")
        );
        for (BillPayment billPament : billPayments) {
            BillPayment bill = service.createBill(billPament);
            System.out.println(bill.getId());
        }
    }

//    @Test
    public void testMarshall() throws IOException {
        String data = "{\"accountNumber\":\"43355252\",\"recipientName\":\"Oladipupo Isola\",\"narration\":\"payment\",\"transferAmount\":\"67.87\"}";
        ObjectMapper mapper = new ObjectMapper();
        Transfer transfer = mapper.readValue(data, Transfer.class);
        System.out.println(transfer);
    }
    //    @Test
//    public void utilTest() {
//        try {
//            String hexString = Hex.encodeHexString("joba123".getBytes(Charsets.UTF_8));
//            String x = new String(new Hex().decode(hexString.getBytes()));
//            System.out.println(hexString + "\n" + x);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
