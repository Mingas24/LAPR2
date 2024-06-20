package lapr.project.gpsd.controller;

import lapr.project.gpsd.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import lapr.project.gpsd.registry.*;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;

public class Bootstrap {

    /**
     * Logger for error message displaying
     */
    private static final Logger LOGGER = Logger.getLogger(Bootstrap.class.getName());

    public Bootstrap() {
    }

    /**
     * Return the Clients Registry.
     *
     * @return - List of Registered Clients.
     */
    public List<Client> getClientRegistry() {
        List<Client> clientList = new ArrayList<>();

        Client mariaSantos = new Client("Maria Santos", "100542369", "936565651", "msantos@gmail.com", new PostalAddress("Rua D. João de França, nº 1", "4420-001", "Gondomar"));//("Rua D. João de França, nº 4", new ZipCode("4420-001"), "Gondomar")
        mariaSantos.setPassword("prosdbsts190");
        clientList.add(mariaSantos);

        Client antonioLage = new Client("António Lage", "200542669", "916535661", "aLage@gmail.com", new PostalAddress("R. Gonçalves de Castro, nº 8", "4415-999", "Pedroso"));
        antonioLage.setPassword("aLage1234");
        clientList.add(antonioLage);

        Client anaSantos = new Client("Ana Santos", "110542349", "966535661", "aSantos23@isep.ipp.pt", new PostalAddress("R. do Carvalhido, nº 9", "4250-100", "Porto"));
        anaSantos.setPassword("aSantini456");
        clientList.add(anaSantos);

        Client joanaSantos = new Client("Joana Santos", "210975020", "966545644", "jSantos@isep.ipp.pt", new PostalAddress("R. Cegonheira, nº 3", "4470-528", "Maia"));
        joanaSantos.setPassword("jjSantos23");
        clientList.add(joanaSantos);

        return clientList;
    }

    /**
     * Returns the Category Registry.
     *
     * @return registo das categorias
     */
    public List<Category> getCategoryRegistry() {
        List<Category> categories = new ArrayList<>();

        categories.add(new Category("01", "Plumber"));
        categories.add(new Category("02", "Locksmith"));
        categories.add(new Category("03", "Automotive Mechanic"));
        categories.add(new Category("04", "Cook"));
        categories.add(new Category("05", "Painter"));

        return categories;
    }

    /**
     * Reads the Service Type Registry File.
     *
     * @return - Services Type Registry.
     */
    public List<ServiceType> getServiceTypeRegistry() {
        List<ServiceType> serviceType = new ArrayList<>();

        serviceType.add(new ServiceType("Limited", "LimitedService"));
        serviceType.add(new ServiceType("Fixed", "FixedService"));
        serviceType.add(new ServiceType("Expandable", "ExpandedService"));

        return serviceType;
    }

    /**
     * Reads the Services Registry File.
     *
     * @param catRegistry
     * @param servTypeRegistry
     * @return - Services Registry.
     */
    public List<Service> getServiceRegistry(CategoryRegistry catRegistry, ServiceTypeRegistry servTypeRegistry) {
        List<Service> services = new ArrayList<>();
        ServiceType servType;
        servType = servTypeRegistry.getServiceTypeById("FixedService");
        Service serv1 = servType.newService("01", "Light plumbing", "Intall water tap", 100.0, catRegistry.getCategoryByID("01"));
        serv1.setExtraData("1:0");
        services.add(serv1);
        
        servType = servTypeRegistry.getServiceTypeById("LimitedService");
        Service serv2 = servType.newService("02", "Heavy plumbing", "Pipeline Repair", 40.0, catRegistry.getCategoryByID("01"));
        services.add(serv2);
        
        servType = servTypeRegistry.getServiceTypeById("LimitedService");
        Service serv3 = servType.newService("03", "Gate painting", "Gate painting", 60.0, catRegistry.getCategoryByID("05"));
        services.add(serv3);
        
        servType = servTypeRegistry.getServiceTypeById("ExpandedService");
        Service serv4 = servType.newService("04", "Prepare dinner", "Prepare dinner and clean kitchen", 80.0, catRegistry.getCategoryByID("04"));
        services.add(serv4);
        
        servType = servTypeRegistry.getServiceTypeById("LimitedService");
        Service serv5 = servType.newService("05", "Repair vehicle", "Repair vehicle engine", 80.0, catRegistry.getCategoryByID("03"));
        services.add(serv5);
        
        servType = servTypeRegistry.getServiceTypeById("FixedService");
        Service serv6 = servType.newService("06", "Gate painting", "Gate painting", 90.0, catRegistry.getCategoryByID("05"));
        serv6.setExtraData("1:0");
        services.add(serv6);
        
        return services;
    }

    /**
     * Reads the Geographical Areas Registry File.
     *
     * @param api
     * @return - Geographical Areas Registry.
     */
    public List<GeographicalArea> getGeographicalAreaRegistry(ExternalService api) {
        List<GeographicalArea> areas = new ArrayList<>();

        areas.add(new GeographicalArea("Gondomar-1", 50.0, 10.f, new ZipCode("4420-002"), api));
        areas.add(new GeographicalArea("Gondomar-2", 10.0, 5.f, new ZipCode("4420-570"), api));
        areas.add(new GeographicalArea("Gondomar-3", 20.0, 8.f, new ZipCode("4435-685"), api));
        areas.add(new GeographicalArea("Porto-1", 30.0, 10.f, new ZipCode("4250-108"), api));
        areas.add(new GeographicalArea("Maia-1", 40.0, 5.f, new ZipCode("4470-526"), api));

        return areas;
    }

    /**
     * Reads the Service Providers Registry File.
     *
     * @param areaRegistry
     * @param catReg
     * @return - Service Providers Registry.
     */
    public List<ServiceProvider> getServiceProviderRegistry(GeographicalAreaRegistry areaRegistry, CategoryRegistry catReg) {
        List<ServiceProvider> serviceProviders = new ArrayList<>();
        List<GeographicalArea> areas = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        Availability avai;

        areas.add(areaRegistry.getGeographicalAreaByDesignation("Gondomar-1"));
        areas.add(areaRegistry.getGeographicalAreaByDesignation("Gondomar-2"));
        categories.add(catReg.getCategoryByDesignation("Automotive Mechanic"));
        categories.add(catReg.getCategoryByDesignation("Plumber"));
        ServiceProvider sp = new ServiceProvider("10001", "500324896", "António Dos Santos Patrão", "António Patrão", "aPadrao@gmail.com", new PostalAddress("", "4415-995", ""), areas, categories);
        avai = new Availability (new Date (2019,6,3), new Time(9,0), new Date(2019,6,5), new Time(23,0));
        sp.addAvailability(avai);   
        avai = new Availability (new Date (2019,6,24), new Time(9,0), new Date(2019,6,25), new Time(13,0));
        sp.addAvailability(avai);   
        serviceProviders.add(sp);
        
        areas.clear();
        categories.clear();
        areas.add(areaRegistry.getGeographicalAreaByDesignation("Porto-1"));
        categories.add(catReg.getCategoryByDesignation("Looksmith"));
        categories.add(catReg.getCategoryByDesignation("Cook"));
        categories.add(catReg.getCategoryByDesignation("Painter"));
        sp =(new ServiceProvider("10002", "510324896", "Maria Das Neves Silva", "Maria Silva", "mSilva@hotmail.com", new PostalAddress("", "4420-002", ""), areas, categories));
        avai = new Availability (new Date (2019,6,6), new Time(9,0), new Date(2019,6,7), new Time(23,0));
        sp.addAvailability(avai);   
        avai = new Availability (new Date (2019,6,24), new Time(9,0), new Date(2019,6,25), new Time(22,0));
        sp.addAvailability(avai);
        avai = new Availability (new Date (2019,6,28), new Time(20,0), new Date(2019,6,29), new Time(18,0));
        sp.addAvailability(avai);        
        serviceProviders.add(sp);
        
        areas.clear();
        categories.clear();
        areas.add(areaRegistry.getGeographicalAreaByDesignation("Maia-1"));
        categories.add(catReg.getCategoryByDesignation("Plumber"));
        categories.add(catReg.getCategoryByDesignation("Looksmith"));
        categories.add(catReg.getCategoryByDesignation("Automotive Mechanic"));
        sp=(new ServiceProvider("10003", "510324877", "Joaquina Dos Santos", "Joaquina Dos Santos", "jaquina@hotmail", new PostalAddress("", "4470-526", ""), areas, categories));
        avai = new Availability (new Date (2019,6,7), new Time(9,0), new Date(2019,6,19), new Time(23,0));
        sp.addAvailability(avai);   
        avai = new Availability (new Date (2019,6,28), new Time(20,0), new Date(2019,6,29), new Time(18,0));
        sp.addAvailability(avai);
        serviceProviders.add(sp);

        
        areas.clear();
        categories.clear();
        areas.add(areaRegistry.getGeographicalAreaByDesignation("Maia-1"));
        areas.add(areaRegistry.getGeographicalAreaByDesignation("Gondomar-2"));
        categories.add(catReg.getCategoryByDesignation("Painter"));
        categories.add(catReg.getCategoryByDesignation("Plumber"));
        sp = (new ServiceProvider("10004", "230324822", "Serafim Santos", "Serafim Santos", "sSantos@gmail.com", new PostalAddress("", "4430-601", ""), areas, categories));
        avai = new Availability (new Date (2019,6,28), new Time(20,0), new Date(2019,6,29), new Time(18,0));
        sp.addAvailability(avai);
        serviceProviders.add(sp);
        
        return serviceProviders;
    }

    /**
     * Returns the Application Registry File.
     *
     * @param catReg
     * @return - Application Registry.
     */
    public List<ServiceProviderApplication> getApplicationRegistry(CategoryRegistry catReg) {
        List<ServiceProviderApplication> applications = new ArrayList<>();

        ServiceProviderApplication servProvApp1 = new ServiceProviderApplication("António Dos Santos Patrão", "500324896", "968795236", "aPadrao@gmail.com", new PostalAddress("R. Central", "4415-995", "Crestuma"));
        servProvApp1.newAcademicQualification("Bachelor", "", "");
        servProvApp1.newProfessionalQualification("Professional Training Course of Automotive Mechanic of the Center of Professional Training of Automotive Repair");
        servProvApp1.newProfessionalQualification("Professional license for light and heavy vehicles");
        servProvApp1.newProfessionalQualification("Advanced Course in Automotive Mechanics");
        servProvApp1.registerCategory(catReg.getCategoryByDesignation("Plumber"));
        servProvApp1.registerCategory(catReg.getCategoryByDesignation("Automotive Mechanic"));
        applications.add(servProvApp1);

        ServiceProviderApplication servProvApp2 = new ServiceProviderApplication("Maria Das Neves Silva", "510324896", "928735537", "mSilva@hotmail.com", new PostalAddress("", "4420-002", ""));
        servProvApp2.newAcademicQualification("Bachelor", "", "");
        servProvApp2.newAcademicQualification("Master", "", "");
        servProvApp2.newAcademicQualification("PhD", "", "");
        servProvApp2.newProfessionalQualification("Advanced Course of plumbing and locksmithing");
        servProvApp2.newProfessionalQualification("Professional license for light and heavy vehicles");
        servProvApp2.newProfessionalQualification("Cooking Course");
        servProvApp2.registerCategory(catReg.getCategoryByDesignation("Painter"));
        servProvApp2.registerCategory(catReg.getCategoryByDesignation("Locksmith"));
        servProvApp2.registerCategory(catReg.getCategoryByDesignation("Cook"));
        applications.add(servProvApp2);

        ServiceProviderApplication servProvApp3 = new ServiceProviderApplication("Joaquina Dos Santos", "510324877", "934735567", "jaquina@hotmail", new PostalAddress("Rua Altino Silva Gomes", "4470-526", "Maia"));
        servProvApp3.newAcademicQualification("Bachelor", "", "");
        servProvApp3.newProfessionalQualification("Advanced Course of plumbing and locksmithing");
        servProvApp3.newProfessionalQualification("Advanced Course in Automotive Mechanics");
        servProvApp3.registerCategory(catReg.getCategoryByDesignation("Plumber"));
        servProvApp3.registerCategory(catReg.getCategoryByDesignation("Locksmith"));
        servProvApp3.registerCategory(catReg.getCategoryByDesignation("Automotive Mechanic"));
        applications.add(servProvApp3);

        ServiceProviderApplication servProvApp4 = new ServiceProviderApplication("Serafim Santos", "230324822", "223654987", "sSantos@gmail.com", new PostalAddress("R. Alberto Alves Tavares", "4430-601", "Vila Nova De Gaia"));
        servProvApp4.newAcademicQualification("High School", "", "");
        servProvApp4.newProfessionalQualification("Painter Course");
        servProvApp4.registerCategory(catReg.getCategoryByDesignation("Painter"));
        servProvApp4.registerCategory(catReg.getCategoryByDesignation("Plumber"));
        applications.add(servProvApp4);

        return applications;
    }

    /**
     * Returns the Service Request Registry File.
     *
     * @param clientReg
     * @return - Service Request Registry.
     */
    public List<ServiceRequest> getServiceRequestRegistry(ClientRegistry clientReg, ServiceRegistry servReg) {
        List<ServiceRequest> requests = new ArrayList<>();
        
        Client client;
        PostalAddress postAddr;
        ServiceRequest servReq;
        Service serv;
        String servDesc;
        
        //1
        client = clientReg.getClientByTIN("100542369");
        postAddr = client.getPostalAddress("Rua D. João de França, nº 1", new ZipCode("4420-001"), "Gondomar");
        servReq = new ServiceRequest(client, postAddr);
        serv = servReg.getServiceById("01");
        servDesc = "Close water tap";
        servReq.addServiceDescription(serv, servDesc, new Time(1,0));
        servReq.addSchedule(new Date(2019, 6, 3), new Time(9,0));
        servReq.addSchedule(new Date(2019, 6, 5), new Time(22,0));
        servReq.setCost(150);
        requests.add(servReq);
        
        //2
        client = clientReg.getClientByTIN("100542369");
        postAddr = client.getPostalAddress("Rua D. João de França, nº 1", new ZipCode("4420-001"), "Gondomar");
        servReq = new ServiceRequest(client, postAddr);
        serv = servReg.getServiceById("02");
        servDesc = "Pipeline Repair";
        servReq.addServiceDescription(serv, servDesc, new Time(1,0));
        servReq.addSchedule(new Date(2019, 6, 24), new Time(9,0));
        servReq.addSchedule(new Date(2019, 6, 25), new Time(22,0));
        servReq.setCost(90);
        requests.add(servReq);
        
        //3
        client = clientReg.getClientByTIN("110542349");
        postAddr = client.getPostalAddress("R. do Carvalhido, nº 9", new ZipCode("4250-100"), "Porto");
        servReq = new ServiceRequest(client, postAddr);
        serv = servReg.getServiceById("03");
        servDesc = "Iron gate painting";
        servReq.addServiceDescription(serv, servDesc, new Time(2,0));
        servReq.addSchedule(new Date(2019, 6, 24), new Time(10,0));
        servReq.addSchedule(new Date(2019, 6, 25), new Time(14,30));
        servReq.setCost(90);
        requests.add(servReq);
        
        //4
        client = clientReg.getClientByTIN("110542349");
        postAddr = client.getPostalAddress("R. do Carvalhido, nº 9", new ZipCode("4250-100"), "Porto");
        servReq = new ServiceRequest(client, postAddr);
        serv = servReg.getServiceById("04");
        servDesc = "Prepare dinner and clean kitchen";
        servReq.addServiceDescription(serv, servDesc, new Time(2,0));
        servReq.addSchedule(new Date(2019, 6, 6), new Time(19,0));
        servReq.setCost(190);
        requests.add(servReq);

        //5
        client = clientReg.getClientByTIN("210975020");
        postAddr = client.getPostalAddress("R. Cegonheira, nº 3", new ZipCode("4470-528"), "Maia");
        servReq = new ServiceRequest(client, postAddr);
        serv = servReg.getServiceById("01");
        servDesc = "Water tap repair";
        servReq.addServiceDescription(serv, servDesc, new Time(1,0));
        servReq.addSchedule(new Date(2019, 6, 7), new Time(19,0));
        servReq.setCost(140);
        requests.add(servReq);
        
        //6
        client = clientReg.getClientByTIN("210975020");
        postAddr = client.getPostalAddress("R. Cegonheira, nº 3", new ZipCode("4470-528"), "Maia");
        servReq = new ServiceRequest(client, postAddr);
        serv = servReg.getServiceById("05");
        servDesc = "Repair vehicle engine and change oil";
        servReq.addServiceDescription(serv, servDesc, new Time(1,0));
        servReq.addSchedule(new Date(2019, 6,8), new Time(9,0));
        servReq.setCost(120);
        requests.add(servReq);
        
        //7
        client = clientReg.getClientByTIN("210975020");
        postAddr = client.getPostalAddress("R. Cegonheira, nº 3", new ZipCode("4470-528"), "Maia");
        servReq = new ServiceRequest(client, postAddr);
        serv = servReg.getServiceById("06");
        servDesc = "Gate paiting";
        servReq.addServiceDescription(serv, servDesc, new Time(1,0));
        servReq.addSchedule(new Date(2019, 6, 29), new Time(20,0));
        servReq.setCost(130);
        requests.add(servReq);
        
        return requests;
    }

    public List<ExecutionOrder> getExecutionOrderRegistry(ClientRegistry clientReg, ServiceRegistry servReg, ServiceProviderRegistry spr){
        List<ExecutionOrder> orders=new ArrayList<>();
        Client client;
        int num;
        Date registerDate;
        ServiceRequest servReq;
        AssignedService as;
        ServiceProvider sp;
        PostalAddress postAddr;
        Time timeInit;
        Time timeEnd;
        AssignedSchedule assignedSched;
        Service serv;
        String servDesc;
        ServiceDescription desc;
        //1
        registerDate=new Date(2019,06,05);
        timeInit=new Time(22);
        timeEnd=new Time(23);
        client = clientReg.getClientByTIN("100542369");
        postAddr = client.getPostalAddress("Rua D. João de França, nº 1", new ZipCode("4420-001"), "Gondomar");
        servReq = new ServiceRequest(client, postAddr);
        serv = servReg.getServiceById("01");
        servDesc = "Close water tap";
        servReq.addServiceDescription(serv, servDesc, new Time(1,0));
        //String description, Time duration, Service service
        desc=new ServiceDescription(servDesc, new Time(1), serv);
        servReq.addSchedule(new Date(2019, 6, 3), new Time(9,0));
        servReq.addSchedule(new Date(2019, 6, 5), new Time(22,0));
        servReq.setCost(150);
        assignedSched=new AssignedSchedule(registerDate, timeInit, timeEnd );
        //ServiceProvider sp, ServiceDescription sd, AssignedSchedule sch
        as=new AssignedService(spr.getServiceProviderByEmail("aPadrao@gmail.com"),desc, assignedSched  );
        //int num, Date date, ServiceRequest sr, AssignedService as, ServiceProvider sp, double dist
        orders.add(new ExecutionOrder(1,registerDate,servReq,as, spr.getServiceProviderByEmail("aPadrao@gmail.com"),8.09));
        //2
        registerDate=new Date(2019,06,07);
        timeInit=new Time(19);
        timeEnd=new Time(20);
        client = clientReg.getClientByTIN("210975020");
        postAddr = client.getPostalAddress("R. Cegonheira, nº 3", new ZipCode("4470-528"), "Maia");
        servReq = new ServiceRequest(client, postAddr);
        serv = servReg.getServiceById("01");
        servDesc = "Water tap repair";
        servReq.addServiceDescription(serv, servDesc, new Time(1,0));
        servReq.addSchedule(new Date(2019, 6, 7), new Time(19,0));
        servReq.setCost(140);
        //String description, Time duration, Service service
        desc=new ServiceDescription(servDesc, new Time(1), serv);
        assignedSched=new AssignedSchedule(registerDate, timeInit, timeEnd );
        //ServiceProvider sp, ServiceDescription sd, AssignedSchedule sch
        as=new AssignedService(spr.getServiceProviderByEmail("jaquina@hotmail"),desc, assignedSched  );
        //int num, Date date, ServiceRequest sr, AssignedService as, ServiceProvider sp, double dist
        orders.add(new ExecutionOrder(3,registerDate,servReq,as, spr.getServiceProviderByEmail("jaquina@hotmail"),0.77));
        //3
        registerDate=new Date(2019,06,8);
        timeInit=new Time(9);
        timeEnd=new Time(10);
        client = clientReg.getClientByTIN("210975020");
        postAddr = client.getPostalAddress("R. Cegonheira, nº 3", new ZipCode("4470-528"), "Maia");
        servReq = new ServiceRequest(client, postAddr);
        serv = servReg.getServiceById("05");
        servDesc = "Repair vehicle engine and change oil";
        servReq.addServiceDescription(serv, servDesc, new Time(1,0));
        servReq.addSchedule(new Date(2019, 6,8), new Time(9,0));
        servReq.setCost(120);
        //String description, Time duration, Service service
        desc=new ServiceDescription(servDesc, new Time(1), serv);
        assignedSched=new AssignedSchedule(registerDate, timeInit, timeEnd );
        //ServiceProvider sp, ServiceDescription sd, AssignedSchedule sch
        as=new AssignedService(spr.getServiceProviderByEmail("jaquina@hotmail"),desc, assignedSched  );
        //int num, Date date, ServiceRequest sr, AssignedService as, ServiceProvider sp, double dist
        orders.add(new ExecutionOrder(4,registerDate,servReq,as, spr.getServiceProviderByEmail("jaquina@hotmail"),0.77));
        //4
        registerDate=new Date(2019,06,6);
        timeInit=new Time(19);
        timeEnd=new Time(21);
        client = clientReg.getClientByTIN("110542349");
        postAddr = client.getPostalAddress("R. do Carvalhido, nº 9", new ZipCode("4250-100"), "Porto");
        servReq = new ServiceRequest(client, postAddr);
        serv = servReg.getServiceById("04");
        servDesc = "Prepare dinner and clean kitchen";
        servReq.addServiceDescription(serv, servDesc, new Time(2,0));
        servReq.addSchedule(new Date(2019, 6, 6), new Time(19,0));
        servReq.setCost(190);
        //String description, Time duration, Service service
        desc=new ServiceDescription(servDesc, new Time(1), serv);
        assignedSched=new AssignedSchedule(registerDate, timeInit, timeEnd );
        //ServiceProvider sp, ServiceDescription sd, AssignedSchedule sch
        as=new AssignedService(spr.getServiceProviderByEmail("mSilva@hotmail.com"),desc, assignedSched  );
        //int num, Date date, ServiceRequest sr, AssignedService as, ServiceProvider sp, double dist
        orders.add(new ExecutionOrder(2,registerDate,servReq,as, spr.getServiceProviderByEmail("mSilva@hotmail.com"),9.54));

        return orders;

    }

    public List<Rating> getRatingRegistry(ExecutionOrderRegistry eor){
        List<Rating> rates=new ArrayList<>();
        ExecutionOrder order;
        Rating rate;

        //1
        order=eor.getExecutionOrderByNum(1);
        if(order!=null){
            rate=new Rating(4);
            rate.setExecutionOrder(order);
            rates.add(rate);
        }
        //2
        order=eor.getExecutionOrderByNum(2);
        if(order!=null){
            rate=new Rating(5);
            rate.setExecutionOrder(order);
            rates.add(rate);
        }
        //3
        order=eor.getExecutionOrderByNum(3);
        if(order!=null){
            rate=new Rating(2);
            rate.setExecutionOrder(order);
            rates.add(rate);
        }
        //4
        order=eor.getExecutionOrderByNum(4);
        if(order!=null){
            rate=new Rating(2);
            rate.setExecutionOrder(order);
            rates.add(rate);
        }
        return rates;
    }
}
