package com.financialsystemmanagement.database;

import com.financialsystemmanagement.clientbuilder.ClientBuilder;
import com.financialsystemmanagement.enterprices.Banks;
import com.financialsystemmanagement.operations.Credit;
import com.financialsystemmanagement.operations.Installment;
import com.financialsystemmanagement.users.BankClient;
import com.financialsystemmanagement.users.BankManager;
import com.financialsystemmanagement.users.BankOperator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Database{
    private final String clientRepositoryPath;
    private final String bankRepositoryPath;
    private final String personalRepositoryPath;
    private final String changesRepositoryPath;
    private final String logsRepositoryPath;
    private final String installmentsRepositoryPath;
    private final String creditsRepositoryPath;

    public Database(){
        this.bankRepositoryPath = "/home/mortaldew/Code/OOP_A/OOP-2022-053504-task1/db/Banks.txt";
        this.clientRepositoryPath = "/home/mortaldew/Code/OOP_A/OOP-2022-053504-task1/db/Clients.txt";
        this.personalRepositoryPath = "/home/mortaldew/Code/OOP_A/OOP-2022-053504-task1/db/Personal.txt";
        this.changesRepositoryPath = "/home/mortaldew/Code/OOP_A/OOP-2022-053504-task1/db/Changes.txt";
        this.logsRepositoryPath = "/home/mortaldew/Code/OOP_A/OOP-2022-053504-task1/db/Logs.txt";
        this.installmentsRepositoryPath = "/home/mortaldew/Code/OOP_A/OOP-2022-053504-task1/db/Installments.txt";
        this.creditsRepositoryPath= "/home/mortaldew/Code/OOP_A/OOP-2022-053504-task1/db/1Credits.txt";
    }

    public void saveToBank(List<String> bankLines) throws IOException{
        Files.write(Paths.get(bankRepositoryPath), bankLines);
    }

    public void saveToClients(List<String> clientsLines) throws IOException{
        Files.write(Paths.get(clientRepositoryPath), clientsLines);
    }

    public void saveToPersonal(List<String> personalLines) throws IOException{
        Files.write(Paths.get(personalRepositoryPath), personalLines);
    }

    public void saveToChanges(List<String> changesLines) throws IOException{
        Files.write(Paths.get(changesRepositoryPath), changesLines);
    }

    public void saveToLogs(List<String> logsLines) throws IOException{
        Files.write(Paths.get(logsRepositoryPath), logsLines);
    }
    public void saveToInstallments(List<String> installmentsLines) throws IOException{
        Files.write(Paths.get(installmentsRepositoryPath), installmentsLines);
    }
    public void saveToCredits(List<String> creditsLines) throws IOException{
        Files.write(Paths.get(creditsRepositoryPath), creditsLines);
    }

    public List<String> loadFromBank() throws IOException {
        return Files.readAllLines(Paths.get(bankRepositoryPath));
    }

    public List<String> loadFromClients() throws IOException {
        return Files.readAllLines(Paths.get(clientRepositoryPath));
    }

    public List<String> loadFromPersonal() throws IOException {
        return Files.readAllLines(Paths.get(personalRepositoryPath));
    }

    public List<String> loadFromChanges() throws IOException {
        return Files.readAllLines(Paths.get(changesRepositoryPath));
    }

    public List<String> loadFromLogs() throws IOException {
        return Files.readAllLines(Paths.get(logsRepositoryPath));
    }

    public List<String> loadFromInstallments() throws IOException {
        return Files.readAllLines(Paths.get(installmentsRepositoryPath));
    }

    public List<String> loadFromCredits() throws IOException {
        return Files.readAllLines(Paths.get(creditsRepositoryPath));
    }


    public Banks deserializeBank(String lines){
        String[] buf = lines.split("/");
        String[] clientBuf = buf[6].split("%");
        List<Integer> list = new ArrayList<>();
        for (String a: clientBuf) {
            list.add(Integer.parseInt(a));
        }
        return new Banks(buf[1], buf[2], buf[3], buf[4],
                Integer.parseInt(buf[5]), Integer.parseInt(buf[0]), list);
    }

    public BankClient deserializeClient(String lines){
        ClientBuilder builder = new ClientBuilder();
        String[] buf = lines.split("/");
        return builder.setUserId(Integer.parseInt(buf[0])).setPersonalName(buf[1]).setPassword(buf[2]).
                setPassportNumber(buf[3]).setIdentificationNumber(buf[4]).setPhoneNumber(buf[5]).setEmail(buf[6]).
                setMoneyCount(Integer.parseInt(buf[7])).setBankId(Integer.parseInt(buf[8])).
                setBlocked(Boolean.parseBoolean(buf[9])).getResult();
    }

    public List<BankOperator> deserializeOperator(List<String> lines){
        List<BankOperator> lst = new ArrayList<BankOperator>();
        for (String s:lines) {
            String[] personal = s.split("/");
            if(personal[1].equals("Operator")){
                lst.add(new BankOperator(personal[1],personal[2],Integer.parseInt(personal[0]),
                        Boolean.parseBoolean(personal[3])));
            }
        }
        return lst;
    }

    public List<BankManager> deserializeManager(List<String> lines){
        List<BankManager> lst = new ArrayList<BankManager>();
        for (String s:lines) {
            String[] personal = s.split("/");
            if (personal[1].equals("Manager")) {
                lst.add(new BankManager(personal[1], personal[2], Integer.parseInt(personal[0]),
                        Boolean.parseBoolean(personal[3])));
            }
        }
        return lst;
    }

    public Installment deserializeInstallment(String lines){
        String[] installment = lines.split("/");
        return new Installment(Integer.parseInt(installment[0]),Integer.parseInt(installment[1]),
                Integer.parseInt(installment[2]));
    }

    public Credit deserializeCredit(String lines){
        String[] credit = lines.split("/");
        return new Credit(Integer.parseInt(credit[0]),Integer.parseInt(credit[1]),
                Integer.parseInt(credit[2]), Integer.parseInt(credit[3]),Boolean.parseBoolean(credit[4]));
    }

    public String serializeInstallment(Installment installment){
        return installment.getUserId() + "/" + installment.getSum() + "/" + installment.getTerms();
    }

    public String serializeCredit(Credit credit){
        return credit.getUserId() + "/" + credit.getSum() + "/" + credit.getTerms() + "/" +
                credit.getCommission() + "/" + credit.isConfirmed();
    }

    public String serializePersonal(BankManager bankManager){
        return bankManager.getUserId() + "/" + bankManager.getPersonalName() + "/" + bankManager.getPassword()
                + "/" + bankManager.getIsCancel();
    }

    public String serializePersonal(BankOperator bankOperator){
        return bankOperator.getUserId() + "/" + bankOperator.getPersonalName() + "/" + bankOperator.getPassword()
                + "/" + bankOperator.getIsCancel();
    }

    public String serializeClient(BankClient client){
        return client.getUserId() + "/" + client.getPersonalName() + "/" + client.getPassword() + "/" +
                client.getPassportNumber() + "/" + client.getIdentificationNumber() + "/" +
                client.getPhoneNumber() + "/" + client.getEmail()+ "/" + client.getMoneyCount() + "/" +
                client.getBankId() + "/" + client.getIsBlocked();
    }

    public String serializeBank(Banks banks){
        StringBuilder clientList = new StringBuilder();
        for (int s: banks.getClientsList()) {
            clientList.append(s).append("%");
        }
        return banks.getBankId() + "/" + banks.getEnterpriseType() + "/" + banks.getEnterpriseName() + "/" +
                banks.getBikOfBank() + "/" + banks.getAddress() + "/" + banks.getUNP() + "/" + clientList;
    }

    public String serializeTransfer(BankClient client1, BankClient client2, int sum, int bankId){
        return client1.getUserId() + "/" + client2.getUserId() + "/" + sum + "/" + bankId;
    }

}
