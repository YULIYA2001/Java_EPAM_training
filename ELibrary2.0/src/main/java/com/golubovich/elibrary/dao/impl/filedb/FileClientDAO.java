package com.golubovich.elibrary.dao.impl.filedb;

import static com.golubovich.elibrary.utils.Constants.CLIENT_DB_TABLE;

import com.golubovich.elibrary.beans.Client;
import com.golubovich.elibrary.dao.DAOException;
import com.golubovich.elibrary.dao.api.ClientDAO;
import com.golubovich.elibrary.utils.JSONSerializer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileClientDAO implements ClientDAO {

    public boolean create(Client client) throws DAOException {
        try(FileWriter fw = new FileWriter(CLIENT_DB_TABLE, true))
        {
            String jsonClientString = JSONSerializer.toJSON(client);
            fw.write(jsonClientString + '\n');
            fw.flush();
            return true;
        }
        catch(IOException e){
            throw new DAOException(e);
        }
    }

    public List<Client> read() throws DAOException {
        ArrayList<Client> clients = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(CLIENT_DB_TABLE))) {
            while (scanner.hasNextLine()) {
                clients.add(JSONSerializer.fromJSON(scanner.nextLine().trim(), Client.class));
                Client.decrementCount();
            }
        } catch (FileNotFoundException e) {
            throw new DAOException(e);
        }

        // Client.getCount();
        return clients;
    }

    public void update(Client currentClient, Client updatedClient) throws DAOException {
        String jsonCurrentClientString = JSONSerializer.toJSON(currentClient);
        String jsonUpdatedClientString = JSONSerializer.toJSON(updatedClient);
        File inputFile = new File(CLIENT_DB_TABLE);
        File temporaryFile = new File("temp.json");

        try (BufferedReader fr = new BufferedReader(new FileReader(inputFile));
            BufferedWriter fw = new BufferedWriter(new FileWriter(temporaryFile))) {

            String s;
            while((s = fr.readLine()) != null){
                if (s.equals(jsonCurrentClientString)) {
                    fw.write(jsonUpdatedClientString);
                }
                else {
                    fw.write(s);
                }
                fw.newLine();
            }
            inputFile.delete();
            temporaryFile.renameTo(inputFile);

        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    public boolean delete(Client deletedClient) throws DAOException {
        String jsonDeletedClientString = JSONSerializer.toJSON(deletedClient);
        File inputFile = new File(CLIENT_DB_TABLE);
        File temporaryFile = new File("temp.json");

        try (BufferedReader fr = new BufferedReader(new FileReader(inputFile));
            BufferedWriter fw = new BufferedWriter(new FileWriter(temporaryFile))) {

            String s;
            while((s = fr.readLine()) != null){
                if (s.equals(jsonDeletedClientString)) {
                    continue;
                }
                else {
                    fw.write(s);
                }
                fw.newLine();
            }
            return inputFile.delete() && temporaryFile.renameTo(inputFile);

        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    public Client findByEmail(String eMail) throws DAOException {
        try (Scanner scanner = new Scanner(new File(CLIENT_DB_TABLE))) {
            while (scanner.hasNextLine()) {
                Client client = JSONSerializer.fromJSON(scanner.nextLine().trim(), Client.class);
                Client.decrementCount();

                if (client.getEMail().equals(eMail)) {
                    return client;
                }
            }
            return null;
        } catch (FileNotFoundException e) {
            throw new DAOException(e);
        }
    }
}

