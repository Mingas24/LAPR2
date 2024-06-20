package lapr.project.gpsd.model;

import lapr.project.gpsd.registry.ServiceProviderRegistry;
import lapr.project.gpsd.registry.ServiceRequestRegistry;
import lapr.project.gpsd.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AssignProviderToRequest {
    private String info;
    private Company com;
    private List<SchedulingAlgorithm> algorithmList;
    private ServiceRequestRegistry srr;
    private ServiceProviderRegistry spr;

    public AssignProviderToRequest(String info, Company com) {
        this.info = info;
        this.com=com;
    }

    public String getInfo() {
        return info;
    }

    public Company assignProvidersToRequests(){
        this.algorithmList=this.com.getAlgorithmList();
        this.srr=this.com.getServiceRequestRegistry();
        this.spr=this.com.getServiceProviderRegistry();
        List<ServiceProvider> lstProvider = this.spr.getServiceProviderList();
        List<ServiceRequest> lstRequest=this.srr.getRequestList();
        SchedulingAlgorithm algorithm = selectAlgorithm(lstRequest.size());
        List<ServiceRequest> list = algorithm.execute(checkProviderInArea(lstRequest,lstProvider),lstProvider);
        com.setServiceRequestRegistry(new ServiceRequestRegistry(list));
        return com;
    }

    public SchedulingAlgorithm selectAlgorithm(int requestNumber){
        if(requestNumber<20){
            for(SchedulingAlgorithm salg : this.algorithmList ){
                if (salg instanceof FirstComeFirstServe){
                    return salg;
                }
            }
        }else{
            for(SchedulingAlgorithm salg : this.algorithmList ){
                if (salg instanceof RandomScheduling){
                    return salg;
                }
            }
        }
        return null;
    }

    public List<ServiceRequest> checkProviderInArea(List<ServiceRequest> lstRequests, List<ServiceProvider> lstProvider){
        List<ServiceRequest> returner=new ArrayList<>();
        int iterator=0;
        boolean auxFlag=true;
        ZipCode provCode ;
        ZipCode requestCode;
        double radius;
        double latCodeProv;
        double lonCodeProv;
        double latCodeReq;
        double lonCodeReq;
        for(ServiceRequest request : lstRequests){
            for(ServiceProvider provider : lstProvider ){
                provCode = provider.getPostalAddress().getZipCode();
                requestCode=request.getGeoAre().getCenterZipCode();
                radius=request.getGeoAre().getRadius();
                latCodeProv=provCode.getLatitude();
                lonCodeProv=provCode.getLongitude();
                latCodeReq=requestCode.getLatitude();
                lonCodeReq=requestCode.getLongitude();
                if(Utils.distance(latCodeProv,lonCodeProv,latCodeReq,lonCodeReq)<=radius && !returner.contains(request)){
                    returner.add(request);
                }
            }
        }
        return returner;
    }




}
