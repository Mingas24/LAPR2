package lapr.project.gpsd.controller;

import lapr.project.gpsd.model.*;
import lapr.project.gpsd.registry.*;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BootstrapTest {

    private Bootstrap bootstrapUnderTest;

    @BeforeEach
    public void setUp() {
        bootstrapUnderTest = new Bootstrap();
    }

    @Test
    public void testGetClientRegistry() {
        // Setup
        List<Client> expectedResult = new ArrayList<>();

        Client mariaSantos = new Client("Maria Santos", "100542369", "936565651", "msantos@gmail.com", new PostalAddress("Rua D. João de França, nº 1", "4420-001", "Gondomar"));//("Rua D. João de França, nº 4", new ZipCode("4420-001"), "Gondomar")
        mariaSantos.setPassword("prosdbsts190");
        expectedResult.add(mariaSantos);

        Client antonioLage = new Client("António Lage", "200542669", "916535661", "aLage@gmail.com", new PostalAddress("R. Gonçalves de Castro, nº 8", "4415-999", "Pedroso"));
        antonioLage.setPassword("aLage1234");
        expectedResult.add(antonioLage);

        Client anaSantos = new Client("Ana Santos", "110542349", "966535661", "aSantos23@isep.ipp.pt", new PostalAddress("R. do Carvalhido, nº 9", "4250-100", "Porto"));
        anaSantos.setPassword("aSantini456");
        expectedResult.add(anaSantos);

        Client joanaSantos = new Client("Joana Santos", "210975020", "966545644", "jSantos@isep.ipp.pt", new PostalAddress("R. Cegonheira, nº 3", "4470-528", "Maia"));
        joanaSantos.setPassword("jjSantos23");
        expectedResult.add(joanaSantos);

        // Run the test
        final List<Client> result = bootstrapUnderTest.getClientRegistry();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetCategoryRegistry() {
        // Setup
        List<Category> categories = new ArrayList<>();

        categories.add(new Category("01", "Plumber"));
        categories.add(new Category("02", "Locksmith"));
        categories.add(new Category("03", "Automotive Mechanic"));
        categories.add(new Category("04", "Cook"));
        categories.add(new Category("05", "Painter"));

        // Run the test
        final List<Category> result = bootstrapUnderTest.getCategoryRegistry();

        // Verify the results
        assertEquals(categories, result);
    }

    @Test
    public void testGetServiceTypeRegistry() {
        // Setup
        List<ServiceType> serviceType = new ArrayList<>();

        serviceType.add(new ServiceType("Limited", "LimitedService"));
        serviceType.add(new ServiceType("Fixed", "FixedService"));
        serviceType.add(new ServiceType("Expandable", "ExpandedService"));

        // Run the test
        final List<ServiceType> result = bootstrapUnderTest.getServiceTypeRegistry();

        // Verify the results
        assertEquals(serviceType, result);
    }

    @Test
    public void testGetServiceRegistry() {
        // Setup
        final CategoryRegistry catRegistry = new CategoryRegistry(bootstrapUnderTest.getCategoryRegistry());
        final ServiceTypeRegistry servTypeRegistry = new ServiceTypeRegistry(bootstrapUnderTest.getServiceTypeRegistry());
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

        // Run the test
        final List<Service> result = bootstrapUnderTest.getServiceRegistry(catRegistry, servTypeRegistry);

        // Verify the results
        assertEquals(services, result);
    }

    @Test
    public void testGetGeographicalAreaRegistry() {
        // Setup
        final ExternalService api = new ExternalServiceAdapter();
        List<GeographicalArea> areas = new ArrayList<>();

        areas.add(new GeographicalArea("Gondomar-1", 50.0, 10.f, new ZipCode("4420-002"), api));
        areas.add(new GeographicalArea("Gondomar-2", 10.0, 5.f, new ZipCode("4420-570"), api));
        areas.add(new GeographicalArea("Gondomar-3", 20.0, 8.f, new ZipCode("4435-685"), api));
        areas.add(new GeographicalArea("Porto-1", 30.0, 10.f, new ZipCode("4250-108"), api));
        areas.add(new GeographicalArea("Maia-1", 40.0, 5.f, new ZipCode("4470-526"), api));

        // Run the test
        final List<GeographicalArea> result = bootstrapUnderTest.getGeographicalAreaRegistry(api);

        // Verify the results
        assertEquals (areas, result);
    }

    @Test
    public void testGetApplicationRegistry() {
        // Setup
        final CategoryRegistry catReg = new CategoryRegistry(bootstrapUnderTest.getCategoryRegistry());
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

        // Run the test
        final List<ServiceProviderApplication> result = bootstrapUnderTest.getApplicationRegistry(catReg);

        // Verify the results
        assertEquals(applications, result);
    }

    @Test
    public void testGetServiceRequestRegistry() {
        // Setup
        final ClientRegistry clientReg = new ClientRegistry(bootstrapUnderTest.getClientRegistry());
        final ServiceTypeRegistry servTypeRegistry = new ServiceTypeRegistry(bootstrapUnderTest.getServiceTypeRegistry());
        final CategoryRegistry catReg = new CategoryRegistry(bootstrapUnderTest.getCategoryRegistry());
        final ServiceRegistry servReg = new ServiceRegistry(bootstrapUnderTest.getServiceRegistry(catReg, servTypeRegistry));
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

        // Run the test
        final List<ServiceRequest> result = bootstrapUnderTest.getServiceRequestRegistry(clientReg, servReg);

        // Verify the results
        assertEquals(requests, result);
    }

    @Test
    public void testGetExecutionOrderRegistry() {
        // Setup
        final GeographicalAreaRegistry areaRegistry = new GeographicalAreaRegistry(bootstrapUnderTest.getGeographicalAreaRegistry(new ExternalServiceAdapter()));
        final ClientRegistry clientReg = new ClientRegistry(bootstrapUnderTest.getClientRegistry());
        final ServiceTypeRegistry servTypeRegistry = new ServiceTypeRegistry(bootstrapUnderTest.getServiceTypeRegistry());
        final CategoryRegistry catReg = new CategoryRegistry(bootstrapUnderTest.getCategoryRegistry());
        final ServiceRegistry servReg = new ServiceRegistry(bootstrapUnderTest.getServiceRegistry(catReg, servTypeRegistry));
        final ServiceProviderRegistry spr = new ServiceProviderRegistry(bootstrapUnderTest.getServiceProviderRegistry(areaRegistry,catReg));
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


        // Run the test
        final List<ExecutionOrder> result = bootstrapUnderTest.getExecutionOrderRegistry(clientReg, servReg, spr);

        // Verify the results
        assertEquals(orders, result);
    }

    @Test
    public void testGetRatingRegistry() {
        // Setup
        final GeographicalAreaRegistry areaRegistry = new GeographicalAreaRegistry(bootstrapUnderTest.getGeographicalAreaRegistry(new ExternalServiceAdapter()));
        final ClientRegistry clientReg = new ClientRegistry(bootstrapUnderTest.getClientRegistry());
        final ServiceTypeRegistry servTypeRegistry = new ServiceTypeRegistry(bootstrapUnderTest.getServiceTypeRegistry());
        final CategoryRegistry catReg = new CategoryRegistry(bootstrapUnderTest.getCategoryRegistry());
        final ServiceRegistry servReg = new ServiceRegistry(bootstrapUnderTest.getServiceRegistry(catReg, servTypeRegistry));
        final ServiceProviderRegistry spr = new ServiceProviderRegistry(bootstrapUnderTest.getServiceProviderRegistry(areaRegistry,catReg));
        final ExecutionOrderRegistry eor = new ExecutionOrderRegistry(bootstrapUnderTest.getExecutionOrderRegistry(clientReg,servReg,spr));
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


        // Run the test
        final List<Rating> result = bootstrapUnderTest.getRatingRegistry(eor);

        // Verify the results
        assertEquals(rates, result);
    }
}
