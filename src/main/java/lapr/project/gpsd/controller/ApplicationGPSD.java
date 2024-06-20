package lapr.project.gpsd.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import lapr.project.authorization.AuthorizationFacade;
import lapr.project.authorization.model.UserSession;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.utils.Constants;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.ExecutionOrder;
import lapr.project.gpsd.model.GeographicalArea;
import lapr.project.gpsd.model.Rating;
import lapr.project.gpsd.model.Service;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.ServiceProviderApplication;
import lapr.project.gpsd.model.ServiceRequest;
import lapr.project.gpsd.model.ServiceType;
import lapr.project.gpsd.model.WriterType;
import lapr.project.gpsd.registry.ApplicationRegistry;
import lapr.project.gpsd.registry.CategoryRegistry;
import lapr.project.gpsd.registry.ClientRegistry;
import lapr.project.gpsd.registry.ExecutionOrderRegistry;
import lapr.project.gpsd.registry.GeographicalAreaRegistry;
import lapr.project.gpsd.registry.ServiceProviderRegistry;
import lapr.project.gpsd.registry.ServiceRegistry;
import lapr.project.gpsd.registry.ServiceRequestRegistry;
import lapr.project.gpsd.registry.ServiceTypeRegistry;
import lapr.project.gpsd.registry.WriterTypeRegistry;

public class ApplicationGPSD {

    private Company company;
    private final AuthorizationFacade authorization;
    private final Bootstrap bootstrap;

    private ApplicationGPSD() {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAM_COMPANY_DESIGNATION),
                props.getProperty(Constants.PARAM_COMPANY_TIN), props.getProperty(Constants.PARAM_TIMER_DELAY), props.getProperty(Constants.PARAM_TIMER_INTERVAL));
        this.authorization = this.company.getAuthorizationFacade();
        this.bootstrap = new Bootstrap();
        bootstrap();
    }

    public Company getCompany() {
        return this.company;
    }

    public UserSession getPresentSession() {
        return this.authorization.getPresentSession();
    }

    public boolean doLogin(String strEmail, String strPwd) {
        return this.authorization.doLogin(strEmail, strPwd) != null;
    }

    public void doLogout() {
        this.authorization.doLogout();
    }

    private Properties getProperties() {
        Properties props = new Properties();

        // Adds Properties and values by omission.
        props.setProperty(Constants.PARAM_COMPANY_DESIGNATION, "Default Ltd.");
        props.setProperty(Constants.PARAM_COMPANY_TIN, "Default TIN");
        props.setProperty(Constants.PARAM_TIMER_DELAY, "1200");
        props.setProperty(Constants.PARAM_TIMER_INTERVAL, "700");

        // Reads properties and defined values. 
        try {
            InputStream in = new FileInputStream(Constants.PARAM_FILE);
            props.load(in);
            in.close();
        } catch (Exception ex) {

        }
        return props;
    }

    private void bootstrap() {
        this.authorization.registerUserRole(Constants.ROLE_ADMIN);
        this.authorization.registerUserRole(Constants.ROLE_CLIENT);
        this.authorization.registerUserRole(Constants.ROLE_HRO);
        this.authorization.registerUserRole(Constants.ROLE_SERVICE_PROVIDER);

        this.authorization.registerUserWithRole("Gonçalo Pinto", "goncalopinto@lapr.pt", "123456", Constants.ROLE_ADMIN);
        this.authorization.registerUserWithRole("Paulo Silva", "paulosilva@lapr.pt", "123456", Constants.ROLE_ADMIN);

        this.authorization.registerUserWithRole("Sandra Luna", "slu@lapr.pt", "123456", Constants.ROLE_HRO);

        this.authorization.registerUserWithRoles("Joaquim Pacheco", "joaquimpacheco@lapr.pt", "123456", new String[]{Constants.ROLE_HRO, Constants.ROLE_ADMIN});

        List<Client> clientList = this.bootstrap.getClientRegistry();
        ClientRegistry cliReg = this.company.getClientRegistry();
        for (Client cli : clientList) {
            cliReg.registerClient(cli, cli.getPassword(), this.authorization);
        }

        List<Category> categoryList = this.bootstrap.getCategoryRegistry();
        CategoryRegistry catReg = this.company.getCategoryRegistry();
        catReg.setCategoryList(categoryList);

        List<ServiceType> servTypeRegistry = this.bootstrap.getServiceTypeRegistry();
        ServiceTypeRegistry servTypeReg = this.company.getServiceTypeRegistry();
        for (ServiceType st : servTypeRegistry) {
            servTypeReg.registerServiceType(st);
        }

        List<Service> serviceList = this.bootstrap.getServiceRegistry(catReg, servTypeReg);
        ServiceRegistry servReg = this.company.getServiceRegistry();
        servReg.setServiceList(serviceList);

        List<GeographicalArea> geoAreaList = this.bootstrap.getGeographicalAreaRegistry(this.company.getApi());
        GeographicalAreaRegistry gaReg = this.company.getGeographicalAreaRegistry();
        for (GeographicalArea ga : geoAreaList) {
            gaReg.registerGeographicalArea(ga);
        }

        List<ServiceProvider> servProvList = this.bootstrap.getServiceProviderRegistry(this.company.getGeographicalAreaRegistry(), this.company.getCategoryRegistry());
        ServiceProviderRegistry servProvReg = this.company.getServiceProviderRegistry();
        for(ServiceProvider sp : servProvList)
            servProvReg.registerServiceProvider(sp, authorization);
//        servProvReg.setServiceProviderList(servProvList);

        List<ServiceProviderApplication> applicationList = this.bootstrap.getApplicationRegistry(this.company.getCategoryRegistry());
        ApplicationRegistry appliReg = this.company.getApplicationRegistry();
        appliReg.setApplicationsList(applicationList);

        List<ServiceRequest> serviceRequestList = this.bootstrap.getServiceRequestRegistry(cliReg, servReg);
        ServiceRequestRegistry servReqReg = this.company.getServiceRequestRegistry();
        for (ServiceRequest servReq : serviceRequestList) {
            servReqReg.registerRequest(servReq, gaReg);
        }

        WriterTypeRegistry wtr = this.company.getWriterTypeRegistry();
        wtr.registerWriterType(new WriterType("XML"));
        wtr.registerWriterType(new WriterType("XLSX"));
        wtr.registerWriterType(new WriterType("CSV"));
        
        List<ExecutionOrder> executionList = this.bootstrap.getExecutionOrderRegistry(cliReg, servReg, servProvReg);
        ExecutionOrderRegistry execOrdReg = this.company.getExecutionOrderRegistry();
        execOrdReg = new ExecutionOrderRegistry(executionList);
        this.company.setExecutionOrderRegistry(execOrdReg);
//        for(ExecutionOrder execOrd: executionList)
//            execOrdReg.registerExecutionOrder();


        Rating rat = new Rating(4);
        rat.setExecutionOrder(execOrdReg.getExecutionOrderByNum(1));
        servProvReg.getServiceProviderByEmail("aPadrao@gmail.com").addRating(rat);
        
        rat = new Rating(5);
        rat.setExecutionOrder(execOrdReg.getExecutionOrderByNum(2));
        servProvReg.getServiceProviderByEmail("mSilva@hotmail.com").addRating(rat);
        

        rat = new Rating(2);
        rat.setExecutionOrder(execOrdReg.getExecutionOrderByNum(3));
        servProvReg.getServiceProviderByEmail("jaquina@hotmail").addRating(rat);
        rat = new Rating(2);
        rat.setExecutionOrder(execOrdReg.getExecutionOrderByNum(4));
        servProvReg.getServiceProviderByEmail("jaquina@hotmail").addRating(rat);
        
        Thread t = new Thread(new Runnable() {
            public void run() {
                AssignProviderToRequestController aptrc=new AssignProviderToRequestController();
                aptrc.start();
            }
        });

        t.start();
    }

    // Inspired in https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static ApplicationGPSD singleton = null;

    public static ApplicationGPSD getInstance() {
        if (singleton == null) {
            synchronized (ApplicationGPSD.class) {
                singleton = new ApplicationGPSD();
            }
        }
        return singleton;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
