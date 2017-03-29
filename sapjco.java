//SAP JAVA application Connector using JCO3
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.ext.DestinationDataProvider;

public class StepServer {

    public static void main(String[] args) {

        // This will create a file called mySAPSystem.jcoDestination
        String DESTINATION_NAME1 = "mySAPSystem";
        
        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "YourHostName");
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR,  "System Number");
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "client");
        connectProperties.setProperty(DestinationDataProvider.JCO_USER,   "YourUsername");
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "xxxxx");
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG,   "en");
        createDestinationDataFile(DESTINATION_NAME1, connectProperties);

        // This will use that destination file to connect to SAP
        try {
            JCoDestination destination = JCoDestinationManager.getDestination("mySAPSystem");
            System.out.println("Attributes:");
            System.out.println(destination.getAttributes());
            System.out.println();
            destination.ping();
        } catch (JCoException e) {
            e.printStackTrace();
        }
        try{
        JCoDestination destination = JCoDestinationManager.getDestination(DESTINATION_NAME1);
        JCoFunction function = destination.getRepository().getFunction("STFC_CONNECTION");
        if (function == null)
            throw new RuntimeException("Not found in SAP.");
        function.getImportParameterList().setValue("REQUTEXT", "Hello SAP");
      
        try
        {
            function.execute(destination);
        }
        catch (AbapException e)
        {
            System.out.println(e.toString());
            return;
        }
        System.out.println("STFC_CONNECTION finished:");
        System.out.println(" Echo: " + function.getExportParameterList().getString("ECHOTEXT"));
        System.out.println(" Response: " + function.getExportParameterList().getString("RESPTEXT"));
        System.out.println();
        }
        catch (JCoException e)
        {
            System.out.println(e.toString());
            return;
        }
    }

	private static void createDestinationDataFile(String destinationName, Properties connectProperties) {
		// TODO
		File destCfg = new File(destinationName+".jcoDestination");
        try
        {
            FileOutputStream fos = new FileOutputStream(destCfg, false);
            connectProperties.store(fos, "for tests only !");
            fos.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unable to create the destination files", e);
        }
		
	}		
}
