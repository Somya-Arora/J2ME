import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.io.IOException ;

/**
 * @author  Saurabh Jain
 * @version 1.0
 * Practice Example : 10
 *
 * An example which shows how to use the Canvas and the Graphics class
*/

public class CanvasMIDlet extends MIDlet implements CommandListener
{
	// The variable for knowing the initialization state
    private boolean init ;

    private Display display ;
    private CanvasDemo cDemo ;
    private List list ;
    private int setInt ;
    private Image image ;
    private Command back ;
        
    public CanvasMIDlet()
    {
		// NOTE : Heavy initialisations should be avoided in the constructor of the MIDlet.
		// The MIDlet is in the paused state when the constructor is called.
		// It does not have access to the resources it needs at this stage.
		// Therefore all heavy initialisations should be done in the way shown below in 
		// the startApp method.
    }

    public void startApp() 
    {
		// NOTE : The startApp method is called whenever the Application Management Software(AMS)
		// decides that the MIDlet needs to get activated.
		// During the lifecycle of particular instance of the application this situation may arise
		// many times. Therefore to save un-necessary initialisations every time the startApp method
		// is called by the AMS, we should use a boolean variable to control the number of initialisations to 1.
		if (init == false)
		{
			// This is the first step to using the user interface in MIDP
			// The statement sets the Display object in our display variable
			// This display object is unique to this MIDlet
			this.display = Display.getDisplay(this) ;

			// Creating the image to be used in the application
			try
			{
				this.image = Image.createImage("/icon.png" ) ;
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace() ;
			}

			// Initialising the list
			this.initList() ;
			
			// Initialising the custom Canvas class
			this.cDemo = new CanvasDemo(this) ;

			init = true ;
		}
        
        this.display.setCurrent(this.list) ;
    }
    
    public void pauseApp()
	{

    }
    
    /**
     * Destroy must cleanup everything not handled by the garbage collector.
     * In this case there is nothing to cleanup.
     */
    public void destroyApp(boolean unconditional)
	{

    }
    
    private void initCommand()
    {
        this.back = new Command( "Back",Command.BACK,2 ) ;
    }
    
	// Initialising the list
    private void initList()
    {
		// Initialising a new list
        this.list = new List( "Graphics List", List.IMPLICIT ) ;
		
		// Appending the list
        this.list.append( "00. " + "Arc",null ) ;
        this.list.append( "01. " + "Rectangular",null ) ;
        this.list.append( "02. " + "RoundRect",null ) ;
        this.list.append( "03. " + "Line",null ) ;
        this.list.append( "04. " + "FillArc",null ) ;
        this.list.append( "05. " + "FillRect",null ) ;
        this.list.append( "06. " + "FillRoundRect",null ) ;

		// An image has been added with a string at this position in the list
        this.list.append( "07. " + "Image",image ) ;
        
		this.list.append( "08. " + "DrawString",null ) ;
        this.list.append( "09. " + "DrawChar",null ) ;
        this.list.append( "10. " + "DrawCharArray",null ) ;
        this.list.append( "11. " + "DrawSubString",null ) ;
        this.list.setCommandListener( this ) ;
    }
    
    public void commandAction( Command comm, Displayable disp )
    {
        if (disp == this.list)
        {
            if(comm  == List.SELECT_COMMAND)
            {
				this.setInt = this.list.getSelectedIndex() ;
                this.display.setCurrent(cDemo) ;
            }
        }
    }
    
    public int getInt()
    {
        return this.setInt ;
    }
    
    public List getMainList()
    {
        return this.list ;
    }
    
    public void setDisplayable( Displayable disp )
    {
        this.display.setCurrent( disp ) ;
    }
}