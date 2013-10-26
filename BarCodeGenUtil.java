import java.io.File;
import java.io.FileOutputStream;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

/**
 *
 * @author Kaesar ALNIJRES
 *
 */
public class BarCodeGenUtil {

  
    public static void main(String[] args) {
      
           int width = 400;
           int height = 60;
           
             
           BitMatrix bitMatrix;
        try {
            bitMatrix = new Code128Writer().encode("GALS4",BarcodeFormat.CODE_128,width,height,null);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File("C:\\Users\\smd.ORADEV\\Desktop\\HackCode\\zxing_barcode.png")));
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}