/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Jee ^^
 */
public class XmlWriter implements Writer{
    
    private String fileName = "ExecutionOrders";
    private String fileExtension = ".xml";
    private File file;        
    
    public XmlWriter(){
    }
    
    @Override
    public boolean writeExecutionOrders(List<ExecutionOrder> leo) {
        if(!leo.isEmpty())
            file = new File(fileName+"_"+leo.get(0).getServiceProvider().getAbrevName()+fileExtension);
        else
            file = new File(fileName+fileExtension);
        boolean returner = true;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            //  root element
            Element rootElement = doc.createElement("executionOrderList");
            doc.appendChild(rootElement);
            
            for(ExecutionOrder eo : leo){
                int number = eo.getNum();
                String name = eo.getServiceRequest().getClient().getName();
                Double dist = eo.getDistance();
                String category = eo.getAssignedService().getServiceDescription().getService().getCategory().getCatID();
                String servType = eo.getAssignedService().getServiceDescription().getService().getServiceType().getServTypeId();
                String day = eo.getAssignedService().getSchedule().getDay().toAnoMesDiaString();
                String time = eo.getAssignedService().getSchedule().getStartTime().toStringHHMM();
                String postAddr = eo.getServiceRequest().getPostalAddress().getAddress();
                String zipCode = eo.getServiceRequest().getPostalAddress().getZipCode().getZipCode();
                String town = eo.getServiceRequest().getPostalAddress().getTown();
                
                Element execO = doc.createElement("executionOrder");
                
                rootElement.appendChild(execO);

                Element eNum = doc.createElement("number");
                eNum.setTextContent(String.format("%d", number));
                execO.appendChild(eNum);

                Element eName = doc.createElement("name");
                eName.setTextContent(name);
                execO.appendChild(eName);

                Element eDist = doc.createElement("distance");
                eDist.setTextContent(String.format("%.1f", dist));
                execO.appendChild(eDist);

                Element eCat = doc.createElement("category");
                eCat.setTextContent(category);
                execO.appendChild(eCat);
                
                Element eServType = doc.createElement("serviceType");
                eServType.setTextContent(servType);
                execO.appendChild(eServType);
                
                Element eDay = doc.createElement("day");
                eDay.setTextContent(day);
                execO.appendChild(eDay);
                
                Element eTime = doc.createElement("time");
                eTime.setTextContent(time);
                execO.appendChild(eTime);
                
                Element ePostAddr = doc.createElement("postAddr");
                ePostAddr.setTextContent(postAddr);
                execO.appendChild(ePostAddr);
                
                Element eZip = doc.createElement("zip");
                eZip.setTextContent(zipCode);
                execO.appendChild(eZip);
                
                Element eTown = doc.createElement("town");
                eTown.setTextContent(town);
                execO.appendChild(eTown);

            
            rootElement.appendChild(execO);
            }
            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Beautify the format of the resulted XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        } catch (Exception ex) {
            ex.printStackTrace();
            returner = false;
        }
        return returner;

    }
}    
    

