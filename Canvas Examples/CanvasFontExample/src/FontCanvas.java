import javax.microedition.lcdui.*;

/**
 * @author  Saurabh Jain
 * @version 1.0
 * Practice Example : 11
 *
 * An example which shows how to use the Canvas and the Font class
*/

public class FontCanvas extends Canvas implements CommandListener 
{
    private FontMIDlet parent ;
    private Font font ;
    
    // Declaring the Command
    private Command face ;
    private Command style ;
    private Command size ;
    
    private Command back ;
    
    // Declaring the lists
    private List faceList ;
    private List styleList ;
    private List sizeList ;
    
    private int faceInt ;
    private int styleInt ;
    private int sizeInt ;
    
    private int displayInt ;
    
    public FontCanvas( FontMIDlet parent ) 
    {
        super() ;
        this.parent = parent ;
        
        this.initBackCommand() ;

        // Initilizing list and commands
        this.initFaceCommand() ;
        this.initFaceList() ;
        
        this.initStyleCommand() ;
        this.initStyleList() ;
        
        this.initSizeCommand() ;
        this.initSizeList() ;
        
        this.initFont() ;
        this.faceInt = Font.FACE_MONOSPACE ;
        this.styleInt = Font.STYLE_PLAIN ;
        this.sizeInt = Font.SIZE_LARGE ;
        
        this.displayInt = 0 ;        
    }
    
    private void initBackCommand() 
    {
        this.back = new Command( "Back",Command.BACK,2 ) ;
        this.addCommand( this.back ) ;
        this.setCommandListener( this ) ;
    }
    
    private void initFaceCommand() 
    {
        this.face = new Command( "Face",Command.ITEM,1 ) ;
        this.addCommand( this.face ) ;
        this.setCommandListener( this ) ;
    }
    
    private void initStyleCommand() 
    {
        this.style = new Command( "Style",Command.ITEM,2 ) ;
        this.addCommand( this.style ) ;
        this.setCommandListener( this ) ;
    }
    
    private void initSizeCommand() 
    {
        this.size = new Command( "Size",Command.ITEM,1 ) ;
        this.addCommand( this.size ) ;
        this.setCommandListener( this ) ;
    }
    
    private void initFont() 
    {
        this.font = Font.getFont(this.faceInt,this.styleInt,this.sizeInt) ;
    }
    
    protected void paint( Graphics g ) 
    {
		// Clearing the screen
        g.setColor( 255,255,255 ) ;
        g.fillRect( 0,0,getWidth(),getHeight()) ;
        
		// Setting the color and font
		g.setColor( 0,0,255 ) ;
        g.setFont(Font.getFont(this.faceInt,this.styleInt,this.sizeInt)) ;
        
		// Draing the string
		g.drawString("FONT EXAMPLE",10,10,Graphics.TOP | Graphics.LEFT) ;
    }
    
    public void commandAction( Command comm, Displayable disp ) 
    {
        if ( disp == this ) 
        {
            if( comm == this.face ) 
            {
                this.displayInt = 1 ;
                this.parent.setDisplayable( this.faceList ) ;
            }
            
            else if( comm == this.style ) 
            {
                this.displayInt = 2 ;
                this.parent.setDisplayable( this.styleList ) ;
            }
            
            else if( comm == this.size ) 
            {
                this.displayInt = 3 ;
                this.parent.setDisplayable( this.sizeList ) ;
            }
        }
        
        if ( comm == this.back ) 
        {
            if ( this.displayInt == 1 )
            {
                this.parent.setDisplayable( this.faceList ) ;   
            }
            
            else if ( this.displayInt == 2 )
            {
                this.parent.setDisplayable( this.styleList ) ;   
            }
            
            if ( this.displayInt == 3 )
            {
                this.parent.setDisplayable( this.sizeList ) ;   
            }
        }
        
        // This is for the Face list display
        if( disp == this.faceList ) 
        {
            if ( comm == List.SELECT_COMMAND ) 
            {
                if( this.faceList.getSelectedIndex() == 0 ) 
                {
                    this.faceInt = Font.FACE_MONOSPACE ;
                }
                else if( this.faceList.getSelectedIndex() == 1 ) 
                {
                    this.faceInt = Font.FACE_PROPORTIONAL ;
                }
                else if( this.faceList.getSelectedIndex() == 2 ) 
                {
                    this.faceInt = Font.FACE_SYSTEM ;
                }

				this.parent.setDisplayable( this ) ;
            }
        }
        
        // This if for displaying the Size list
        if ( disp == this.sizeList ) 
        {
            if( comm == List.SELECT_COMMAND ) 
            {
                if ( this.sizeList.getSelectedIndex() == 0 ) 
                {
                    this.sizeInt = Font.SIZE_LARGE ;
                }
                
                else if ( this.sizeList.getSelectedIndex() == 1 ) 
                {
                    this.sizeInt = Font.SIZE_MEDIUM ;
                }
                
                else if ( this.sizeList.getSelectedIndex() == 2 ) 
                {
                    this.sizeInt = Font.SIZE_SMALL ;
                }

				this.parent.setDisplayable( this ) ;
            }
        }
        
        // This is for the Style list
        if ( disp == this.styleList ) 
        {
            if ( comm == List.SELECT_COMMAND ) 
            {
				switch(this.styleList.getSelectedIndex())
				{
					case 0:
					{
						this.styleInt = Font.STYLE_BOLD + Font.STYLE_UNDERLINED + Font.STYLE_ITALIC ;
						break ;
					}
					
					case 1:
					{
						this.styleInt = Font.STYLE_BOLD + Font.STYLE_ITALIC;
						break ;
					}

					case 2:
					{
						this.styleInt = Font.STYLE_BOLD ;
						break ;
					}

					case 3:
					{
						this.styleInt = Font.STYLE_ITALIC + Font.STYLE_UNDERLINED ;
						break ;
					}

					case 4:
					{
						this.styleInt = Font.STYLE_ITALIC ;
						break ;
					}

					case 5:
					{
						this.styleInt = Font.STYLE_UNDERLINED ;
						break ;
					}

					case 6:
					{
						this.styleInt = Font.STYLE_PLAIN ;
						break ;
					}

					case 7:
					{
						this.styleInt = Font.STYLE_BOLD + Font.STYLE_UNDERLINED ;
						break ;
					}
				}

				this.parent.setDisplayable( this ) ;
            }
        }

        repaint() ;
    }
    
    private void initFaceList() 
    {
        this.faceList = new List( "FontFaceList",List.IMPLICIT ) ;
        this.faceList.append( "01." + " " + "Font-Monospace",null ) ;
        this.faceList.append( "02." + " " + "Font-Propertional",null ); 
        this.faceList.append( "03." + " " + "Font-System",null ) ;
        this.faceList.setCommandListener( this ) ;
    }
    
    private void initStyleList() 
    {
        this.styleList = new List( "FontStyleList",List.IMPLICIT ) ;
        this.styleList.append( "01." + " " + "Font-B+I+U",null ) ;
        this.styleList.append( "02." + " " + "Font-B+I",null ) ;
        this.styleList.append( "03." + " " + "Font-Bold",null ) ;
        this.styleList.append( "04." + " " + "Font-I+U",null ) ;
        this.styleList.append( "05." + " " + "Font-Italic",null ) ;
        this.styleList.append( "06." + " " + "Font-Underline",null ) ;
        this.styleList.append( "07." + " " + "Font-Plain",null ) ;
        this.styleList.append( "08." + " " + "Font-B+U",null ) ;
        this.styleList.setCommandListener( this ) ;
    }
    
    private void initSizeList() 
    {
        this.sizeList = new List( "FontSizeList",List.IMPLICIT ) ;
        this.sizeList.append( "01." + " " + "Font-Large",null ) ;
        this.sizeList.append( "02." + " " + "Font-Medium",null ) ;
        this.sizeList.append( "03." + " " + "Font-Small",null ) ;
        this.sizeList.setCommandListener( this ) ;
    }
}
