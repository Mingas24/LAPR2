package lapr.project.gpsd.registry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;
import lapr.project.authorization.AuthorizationFacade;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.utils.Constants;
import lapr.project.gpsd.model.PostalAddress;

public class ClientRegistry{

    private List<Client> clientList;

    /**
     * Constructor with the list of Clients.
     * @param clientsList - List of registered Clients.
     */
    public ClientRegistry(List<Client> clientsList){
        this.clientList = clientsList;
    }
    
    /**
     * Default constructor.
     */
    public ClientRegistry(){
        this.clientList = new ArrayList<>();
    }    
    
    public List<Client> getClientList(){
        return this.clientList;
    }
    
    /**
     * Creates a new Client.
     * @param strName
     * @param strTIN
     * @param strTelephone
     * @param strEmail
     * @param address
     * @return 
     */
    public Client newClient(String strName, String strTIN, String strTelephone, String strEmail, String password, PostalAddress address){
        Client cli = new Client(strName, strTIN, strTelephone, strEmail, address);
        cli.setPassword(password);
        return cli;
    }
    
    /**
     * Validates Client - Checks if the email already exists and confirms a password was entered.
     * @param client
     * @param password
     * @return 
     */
    public boolean validateClient(Client client, String password){
        boolean bVal = true;
        if (client == getClientByEmail(client.getEmail()))
            bVal = false;
        if (password == null)
            bVal = false;
        if (password.isEmpty())
            bVal = false;
        return bVal;
    }
    
    /**
     * Regists the client.
     * @param client
     * @param password
     * @param af
     * @return 
     */
    public boolean registerClient(Client client, String password, AuthorizationFacade af){
        if (validateClient(client, password)){
            if (af.registerUserWithRole(client.getName(), client.getEmail(), password, Constants.ROLE_CLIENT))
                return addClient(client);
        }
        return false;
    }
    
    /**
     * Adds the Client to the List.
     * @param cli
     * @return 
     */
    public boolean addClient(Client cli){
        return this.clientList.add(cli);
    }
        
    /**
     * Returns the client with the given email.
     * @param email
     * @return Client pretendido
     */
    public Client getClientByEmail(String email){
        for(Client client: clientList){
            if(client.hasEmail(email))
                return client;
        }
        return null;
    }
    
    public Client getClientByTIN(String tin){
        for (Client cli: this.clientList){
            if (cli.hasTIN(tin))
                return cli;
        }
        return null;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ClientRegistry that = (ClientRegistry) o;
        return clientList.equals(that.clientList);
    }
}