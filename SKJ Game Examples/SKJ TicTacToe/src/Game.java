/*
 * Game.java
 *
 * Created on March 5, 2003, 7:15 PM
 */

import javax.microedition.midlet.* ;
import javax.microedition.lcdui. * ;
import java.util.* ;

/**
 *
 * @author Saurabh Jain
 * @version 1.0
 */

public class Game extends Canvas implements CommandListener 
{
    // Defining the instance variable for this class
    private int userWins ;
    private int compWins ;
    
    // Defining the Image variables
    //private Image blankImage ;
    private Image zeroImage ;
    private Image crossImage ;
    
    private int[][] sq ;
    
    private int colSelected ;
    private int rowSelected ;
    
    private NCMIDlet parent ;
    private MainMenu mainMenu ;
    
    private Timer timer ;
    
    // Defining the MaxX(Width of Canvas)  and MaxY(Height of Canvas)
    private int maxX ;
    private int maxY ;
    
    // Defining the command score
    private Command score ;
    private Command newG ;
    private Command back = new Command("Back", Command.BACK, 2) ;
    
    // Defining the Alert for the 
    private Alert alert ;
    
    private int frame ;
    
    private int imgWidth ;
    private int imgHeight ;
    
    private int userSymbol = -1;
    private int compSymbol = -1;
    
    // Variables for AIRun
    private int aa ;
    private int bb ;
    //private int cc ;
    
    // Varibales for animation
    private boolean animated ;
    
    // Utility variables
    private Random random ;
    
    // Defining the constructor for  this class
    public Game( NCMIDlet parent,MainMenu mainMenu ) 
    {   
        this.parent = parent ;
        this.mainMenu = mainMenu ;
        this.init() ;

        if(this.compSymbol == 1)
        {
            this.AIRun() ;
        }
    }
    
    private void newGame()
    {
        this.newInit() ;
        if(this.compSymbol == 1)
        {
            this.AIRun() ;
        }
    }
    
    public void commandAction( Command comm, Displayable disp ) 
    {
        if ( disp == this )
        {
            if ( comm == this.score )
            {
                this.parent.setDisplayable(this.alert) ;
            }
            else if ( comm == this.newG )
            {
                this.newGame() ;
            }
            else if(comm == this.back)
            {
                this.parent.setDisplayable(this.mainMenu) ;
            }
        }
    }
    
    private void AIRun() 
    {    
        this.aa = 10 ;
        this.bb = 10 ;
        
        if(r1() == false)
        {
            //this.aa = 2 ;
            if(r2() == false)
            {
                //this.aa = 3 ;
                if(r3() == false)
                {
                    //this.aa = 4 ;
                    if(r4() == false)
                    {
                        //this.aa = 5 ;
                        r5() ;
                    }
                }
            }
        }
    }
    
    private boolean r1() // Rule for testing chance for winning
    {
        // Checking for ready options to win across x and y lines			
        //Y Axis
        int x ;
        int y ;
        int c ;

        for(int i=0;i < 3;i++)			
        {
            x = -1 ;
            y = -1 ;
            c = 0 ;
                
            for(int j=0;j < 3;j++)		
            {   
                if(sq[i][j] == this.compSymbol)	
                {
                    c++ ;
                }
				else if(sq[i][j] == -1)	
                {
                    x = i ; 
                    y = j ;
                }
            }
            
            if(x != -1 && y != -1)
            {
                if(c==2 && sq[x][y] == -1)
                {
                    sq[x][y] = this.compSymbol ;
                    this.aa = 1 ;
                    this.bb = 1 ;
                    return true ;
                }
            }
        }

        //X Axis
        for(int i=0;i < 3;i++)
        {
            x = -1 ;
            y = -1 ;
            c = 0 ;
            
            for(int j=0;j < 3;j++)
            {
				if(sq[j][i] == this.compSymbol)
                {
                    c++ ;
                }
				else if(sq[j][i] == -1)	
                {
                    x = j ;
                    y = i ;
                }
            }
            
            if(x != -1 && y != -1)
            {
                if(c==2 && sq[x][y] == -1)
                {
                    sq[x][y] = this.compSymbol ;
                    this.aa = 1 ;
                    this.bb = 2 ;
                    return true ;
                }
            }
        }

        // Diagonal 1
        x = -1 ;
        y = -1 ;
        c = 0 ;
        
        for(int i=0;i < 3;i++)
        {
            if(sq[i][i] == this.compSymbol)
            {
				c++ ;
            }
            else if(sq[i][i] == -1)	
            {
				x = i ;
				y = i ;
            }
        }
        
        if(x != -1 && y != -1)
        {
            if(c==2 && sq[x][y] == -1)
            {
                sq[x][y] = this.compSymbol ;
                this.aa = 1 ;
                this.bb = 3 ;
                return true ;
            }
        }

        // Diagonal 2
        x = -1 ;
        y = -1 ;
        c = 0 ;
        
        for(int i=0;i < 3;i++)
        {
            if(sq[2-i][i] == this.compSymbol)
            {
				c++ ;
            }
            else if(sq[2-i][i] == -1)
            {
				x = 2 - i ;
				y = i ;
            }
        }
        
        if(x != -1 && y != -1)
        {
            if(c == 2 && sq[x][y] == -1)
            {
                sq[x][y] = this.compSymbol ;
                this.aa = 1 ;
                this.bb = 4 ;
                return true ;
            }
        }

        return false ;
    }
    
    private boolean r2() // Rule for testing chance for losing
    {
        // Checking for ready options to win across x and y lines			
        //Y Axis
        int x ;
        int y ;
        int c ;	

        for(int i=0;i < 3;i++)			
        {
            x = -1 ;
            y = -1 ;
            c = 0 ;
                
            for(int j=0;j < 3;j++)		
            {   
                if(sq[i][j] == this.userSymbol)	
                {
                    c++ ;
                }
				else if(sq[i][j] == -1)	
                {
                    x = i ;
                    y = j ;
                }
            }
            
            if(x != -1 && y != -1)
            {
                if(c==2 && sq[x][y] == -1)
                {
                    sq[x][y] = this.compSymbol ;
                    this.aa = 2 ;
                    this.bb = 1 ;
                    return true ;
                }
            }
        }

        //X Axis
        for(int i=0;i < 3;i++)
        {
            x = -1 ;
            y = -1 ;
            c = 0 ;
                
            for(int j=0;j < 3;j++)
            {
		if(sq[j][i] == this.userSymbol)
                {
                    c++ ;
                }
		else if(sq[j][i] == -1)	
                {
                    x = j ;
                    y = i ;
                }
            }
            
            if(x != -1 && y != -1)
            {
                if(c==2 && sq[x][y] == -1)
                {
                    sq[x][y] = this.compSymbol ;
                    this.aa = 2 ;
                    this.bb = 2 ;
                    return true ;
                }
            }
        }

        // Diagonal 1
        x = -1 ;
        y = -1 ;
        c = 0 ;
        
        for(int i=0;i < 3;i++)
        {
            if(sq[i][i] == this.userSymbol)
            {
		c++ ;
            }
            else if(sq[i][i] == -1)	
            {
		x = i ;
		y = i ;
            }
        }
        
        if(x != -1 && y != -1)
        {
            if(c==2 && sq[x][y] == -1)
            {
                sq[x][y] = this.compSymbol ;
                this.aa = 2 ;
                this.bb = 3 ;
                return true ;
            }
        }

        // Diagonal 2
        x = -1 ;
        y = -1 ;
        c = 0 ;
        
        for(int i=0;i < 3;i++)
        {
            if(sq[2-i][i] == this.userSymbol)
            {
		c++ ;
            }
            else if(sq[2-i][i] == -1)
            {
		x = 2-i ;
		y = i ;
            }
        }
        
        if(x != -1 && y != -1)
        {
            if(c==2 && sq[x][y] == -1)
            {
                sq[x][y] = this.compSymbol ;
                this.aa = 2 ;
                this.bb = 4 ;
                return true ;
            }
        }
        
        return false ;
    }
    
    private boolean r3() // Rule for testing for best practices
    {
        // Two corners to opponent and our in middle scenario
        int c = 0 ;
        
        if(sq[0][0] == this.userSymbol)
        {
            c++ ;
        }
        
        if(sq[0][2] == this.userSymbol)
        {
            c++ ;
        }
        
        if(sq[2][0] == this.userSymbol)
        {
            c++ ;
        }
        
        if(sq[2][2] == this.userSymbol)
        {
            c++ ;
        }
        
        if(c > 1 && sq[1][1] == this.compSymbol)
        {
            if(sq[0][1] == -1)
            {
                sq[0][1] = this.compSymbol ;
                return true ;
            }
            else if(sq[1][0] == -1)
            {
                sq[1][0] = this.compSymbol ;
                return true ;
            }
            else if(sq[1][2] == -1)
            {
                sq[1][2] = this.compSymbol ;
                return true ;
            }
            else if(sq[2][1] == -1)
            {
                sq[2][1] = this.compSymbol ;
                return true ;
            }
        }
        
        // Two crosses at specific position to opponent and our in middle scenario
        
        if(sq[0][0] == this.userSymbol && sq[2][1] == this.userSymbol && sq[1][1] == this.compSymbol && sq[1][2] == -1)
        {
            sq[1][2] = this.compSymbol ;
            return true ;
        }
        else if(sq[2][0] == this.userSymbol && sq[1][2] == this.userSymbol && sq[1][1] == this.compSymbol && sq[0][1] == -1)
        {
            sq[0][1] = this.compSymbol ;
            return true ;
        }
        else if(sq[0][2] == this.userSymbol && sq[1][0] == this.userSymbol && sq[1][1] == this.compSymbol && sq[2][1] == -1)
        {
            sq[2][1] = this.compSymbol ;
            return true ;
        }
        else if(sq[2][2] == this.userSymbol && sq[0][1] == this.userSymbol && sq[1][1] == this.compSymbol && sq[1][0] == -1)
        {
            sq[1][0] = this.compSymbol ;
            return true ;
        }
            
        return false ;
    }
    
    private boolean r4() // Rule for testing side squares
    {
        // Putting symbol at middle		
		if(sq[1][1] == -1)
        {
            sq[1][1] = this.compSymbol ;
            return true ;
        }

        // Putting symbol at corners				
		switch(getRandom(1,5)) // Breaks should not be there for AI
        {
            case 1 :
            {
				if(sq[0][0] == -1)
                {
                    sq[0][0] = this.compSymbol ;
                    return true ;
                }
            }
            case 2 :
            {
                if(sq[0][2] == -1)	
                {
                    sq[0][2] = this.compSymbol ;
                    return true ;
                }
            }
            case 3 :
            {
				if(sq[2][2] == -1)	
                {
                    sq[2][2] = this.compSymbol ;
                    return true ;
                }
            }
            case 4 :
            {
				if(sq[2][0] == -1)	
                {
                    sq[2][0] = this.compSymbol ;
                    return true ;
                }
            }
        }
        // Putting symbol at other squares				
        switch(getRandom(1,4))
        {
            case 1 :
            {
                if(sq[0][1] == -1)
                {
                    sq[0][1] = this.compSymbol ;
                    return true ;
                }
            }
            case 2 :	
            {
                if(sq[1][0] == -1)
                {
                    sq[1][0] = this.compSymbol ;
                    return true ;
                }
            }
            case 3 :		
            {
                if(sq[1][2] == -1)
                {
                    sq[1][2] = this.compSymbol ;
                    return true ;
                }
            }
            case 4 :
            {
                if(sq[2][1] == -1)
                {
                    sq[2][1] = this.compSymbol ;
                    return true ;
                }
            }
        }
        
        return false ;
    }
    
     private void r5() // Rule for testing side squares
    {
        for(int i=0;i < 3;i++)
        {
            for(int j=0;j < 3;j++)
            {
                if(sq[i][j] == -1)
                {
                    sq[i][j] = this.compSymbol ;
                    return ;
                }
            }
        }
    }
    
    // for initilization purpose and call in the Game() constructor
    public void init( )
    {
        try 
        { 
            this.userWins = 0 ;
            this.compWins = 0 ;
            
            this.colSelected = 0 ;
            this.rowSelected = 0 ;
            //repaint() ;
            
            // Getting the maximum width and height of the Canvas
            this.maxX = this.getWidth() ;
            this.maxY = this.getHeight() ;

            this.alert = new Alert("Score","User  " + String.valueOf(this.userWins)  + "\nDevice  " + String.valueOf(this.compWins),null, AlertType.INFO ) ;
            
            this.animated = this.parent.getAnimate() ;
            
            // Initilizing the Command 
            this.score = new Command( "Score", Command.ITEM, 2 ) ;
            this.addCommand( this.score ) ;
            //this.setCommandListener( this ) ;
            
            this.newG = new Command( "New", Command.ITEM, 1 ) ;
            this.addCommand( this.newG ) ;
            
            this.addCommand(this.back) ;
            
            this.setCommandListener( this ) ;
            
           this.random = new Random() ;
            
            // Settings the framem variable
            this.frame = 0 ;
            
            
            
            this.sq = new int[3][4] ;
            
            // Initializing the sq[][] array to -1
            for( int i = 0; i < 3;i++ )
            {
                for( int j = 0; j < 3;j++ )
                {
                    this.sq[i][j] = -1 ;
                }
            }
            
            // Setting the userSymbol
            if(this.userSymbol == -1)
            {
                this.userSymbol = 0 ;
            }
            else if(this.userSymbol == 0)
            {
                this.userSymbol = 1 ;
            }
            else if(this.userSymbol == 1)
            {
                this.userSymbol = 0 ;
            }
            
            // Setting the compSymbol
            if(this.compSymbol == -1)
            {
                this.compSymbol = 1 ;
            }
            else if(this.compSymbol == 0)
            {
                this.compSymbol = 1 ;
            }
            else if(this.compSymbol == 1)
            {
                this.compSymbol = 0 ;
            }
            
            // Declaring timer and timertask
            TimerTask task = new TimerTask()
            {
                public void run()
                {
                    if( frame == 0 )
                    {
                        if(animated == true)
                        {
                            frame = 1 ;
                        }
                    }
                    else if( frame == 1 )
                    {
                        if(animated == true)
                        {
                            frame = 0 ;
                        }
                    }

                    // Checking whether all chances are finished
                    int c = 0 ;
                    for(int i=0;i < 3;i++)
                    {
                        for(int j=0;j < 3;j++)
                        {
                            if(sq[i][j] == -1)
                            {
                                c++ ;
                            }
                        }
                    }

                    if(c == 0)
                    {
                        alert = new Alert("Draw !","User  " + String.valueOf(userWins)  + "\nDevice  " + String.valueOf(compWins),null, AlertType.INFO ) ;
                        parent.setDisplayable(alert) ;
                        newGame() ;
                    }
                    
                    repaint() ;
                }
            }; 
        
            timer = new Timer() ;
            timer.scheduleAtFixedRate( task,400,400 ) ;
            
            //Initialising images
            
            if ( this.maxX < 150 && this.maxY < 150 )
            {
                this.imgWidth = 16 ;
                this.imgHeight = 16 ;
                this.crossImage = Image.createImage( "/cross16.png" ) ;
                this.zeroImage = Image.createImage( "/naught16.png" ) ;  
            }
            else
            {
                this.imgWidth = 32 ;
                this.imgHeight = 32 ; 
                this.crossImage = Image.createImage( "/cross32.png" ) ;
                this.zeroImage = Image.createImage( "/naught32.png" ) ;  
            }
        }  
        catch( Exception e ) 
        {
            e.printStackTrace() ;
        }
    }
    
    private void newInit()
    {
        this.colSelected = 0 ;
        this.rowSelected = 0 ;

        this.alert = new Alert("Score","User  " + String.valueOf(this.userWins)  + "\nDevice  " + String.valueOf(this.compWins),null, AlertType.INFO ) ;

        this.animated = this.parent.getAnimate() ;

        this.frame = 0 ;

        this.sq = new int[3][4] ;

        // Initializing the sq[][] array to -1
        for( int i = 0; i < 3;i++ )
        {
            for( int j = 0; j < 3;j++ )
            {
                this.sq[i][j] = -1 ;
            }
        }

        // Setting the userSymbol
        if(this.userSymbol == -1)
        {
            this.userSymbol = 0 ;
        }
        else if(this.userSymbol == 0)
        {
            this.userSymbol = 1 ;
        }
        else if(this.userSymbol == 1)
        {
            this.userSymbol = 0 ;
        }

        // Setting the compSymbol
        if(this.compSymbol == -1)
        {
            this.compSymbol = 1 ;
        }
        else if(this.compSymbol == 0)
        {
            this.compSymbol = 1 ;
        }
        else if(this.compSymbol == 1)
        {
            this.compSymbol = 0 ;
        }
    }
    
    public void paint( Graphics g ) 
    {
        g.setColor( 255,255,255 ) ;
        g.fillRect( 0,0,maxX,maxY ) ;

        g.setColor( 0,0,0 ) ;
        // This vertical line start at x/2 position
        g.fillRect((this.maxX / 3) - 1 , 0,3,this.maxY ) ;

        // This vertical line start at 2x/3 position
        g.fillRect((( 2 * this.maxX) / 3) - 1 , 0,3,this.maxY ) ;

        // This horizontal line start at this.maxY / 3 - 1 position
        g.fillRect( 0, (this.maxY / 3) - 1 , this.maxX , 3 ) ;

        // This horaizental line start at this.maxY / 3 - 1 position
        g.fillRect(0, ((2 * this.maxY ) / 3) - 1 , this.maxX , 3 ) ;

        // Drawing the selecting rectangle
        g.setColor( 255,0,0 ) ;
        
        g.drawRect(((this.colSelected * this.maxX) / 3)-1,((this.rowSelected * this.maxY) / 3) - 1, (this.maxX / 3) + 2,(this.maxY / 3) + 2 );
        g.drawRect((this.colSelected * this.maxX / 3),(this.rowSelected * this.maxY / 3), this.maxX / 3,this.maxY / 3);
        g.drawRect((this.colSelected * this.maxX / 3) + 1,(this.rowSelected * this.maxY / 3) + 1, (this.maxX / 3) - 2,(this.maxY / 3) - 2);

        // Setting X variable for proper placement
        int refX = ((this.maxX/3) - this.imgWidth)/2 ;
        int refY = ((this.maxY/3) - this.imgHeight)/2 ;

        for( int i = 0; i< 3;i++ )
        {
            for( int j = 0; j < 3;j++ )
            {
                if( this.sq[i][j] == 0 )
                {
                    g.setClip(( i * this.maxX/3 ) + refX,( j * this.maxY/3 ) + refY,this.imgWidth,this.imgHeight) ;
                    g.drawImage(this.zeroImage,(( i * this.maxX/3 ) + refX )-this.frame*this.imgWidth,( j * this.maxY/3 ) + refY,Graphics.TOP|Graphics.LEFT );
                }
                else if( this.sq[i][j] == 1 )
                {
                    g.setClip(( i * this.maxX/3 ) + refX,( j * this.maxY/3 ) + refY,this.imgWidth,this.imgHeight) ;
                    g.drawImage(this.crossImage,((i * this.maxX/3) + refX)-this.frame*this.imgWidth,(j * this.maxY/3)+ refY,Graphics.TOP|Graphics.LEFT );
                }

                g.setClip(0,0,this.maxX,this.maxY) ;
            }
        }
    }
    
    public boolean win() 
    {
        // Checking lines along x and y axis		
        int cx ;
        int cy ;
        int cd1 = 0 ;
        int cd2 = 0 ;
        
        for(int i=0;i < 3;i++)
        {
            cx = 0 ;
            cy = 0 ;
            
            // Settinmg counter acoording to sequence
            for(int j=0;j < 3;j++)	
            {
                if(sq[i][j] == this.compSymbol)
                {
                    cx++ ;
                }
                
				if(sq[j][i] == this.compSymbol)
                {
                    cy++ ;
                }
            }
            
            // Checking for completed sequence in x axis and y axis
            if(cx == 3 || cy == 3)
            {
                this.aa = 24 + sq[1][0] ;
                this.bb = 24 + sq[0][1] ;
                return this.compWin() ;
            }
            
            // Checking lines along diagonals
            if(sq[i][i] == this.compSymbol)
            {
                cd1++ ;
            }
            
            if(sq[2-i][i] == this.compSymbol)
            {
                cd2++ ;
            }
        }
        
        // Checking for completed sequence in x axis and y axis
        if(cd1 == 3 || cd2 == 3)
        {
            this.aa = 22 ;
            return this.compWin() ;
        }
        
        // For user
        // Checking lines along x and y axis		
        cx = 0 ;
        cy = 0 ;
        cd1 = 0 ;
        cd2 = 0 ;
        
        for(int i=0;i < 3;i++)
        {
            // Settinmg counter acoording to sequence
            for(int j=0;j < 3;j++)	
            {
                if(sq[i][j] == this.userSymbol)
                {
                    cx++ ;
                }
                
				if(sq[j][i] == this.userSymbol)
                {
                    cy++ ;
                }
            }
            
            // Checking for completed sequence in x axis and y axis
            if(cx == 3 || cy == 3)
            {
                return this.userWin() ;
            }
            else
            {
                cx = 0 ;
                cy = 0 ;
            }
            
            // Checking lines along diagonals
            if(sq[i][i] == this.userSymbol)
            {
                cd1++ ;
            }
            
            if(sq[2-i][i] == this.userSymbol)
            {
                cd2++ ;
            }
        }
        
        // Checking for completed sequence in x axis and y axis
        if(cd1 == 3 || cd2 == 3)
        {
            return this.userWin() ;
        }
        
        return false ;
    }
    
    private boolean compWin()
    {
        this.compWins++ ;
        this.alert = new Alert("Device has won!","User  " + String.valueOf(this.userWins)  + "\nDevice  " + String.valueOf(this.compWins),null, AlertType.INFO ) ;
        parent.setDisplayable(this.alert) ;
        
        // Updating the scores on winning
        this.parent.setHighScore("User " + String.valueOf(this.userWins) + "  Device " + String.valueOf(this.compWins) ) ;
        
        this.newGame() ;
        
        return true ;
    }
    
    private boolean userWin()
    {
        this.userWins++ ;
        this.alert = new Alert("You have won!","User  " + String.valueOf(this.userWins)  + "\nDevice  " + String.valueOf(this.compWins),null, AlertType.INFO ) ;
        parent.setDisplayable(this.alert) ;
        
        // Updating the scores on winning
        this.parent.setHighScore("User " + String.valueOf(this.userWins) + "  Device " + String.valueOf(this.compWins) ) ;
        
        this.newGame() ;
        
        return true ;
    }
    
    public void keyPressed( int keyCode )
    {
        int action = getGameAction( keyCode ) ;
        
        if ( action == Canvas.LEFT )
        {
            if ( this.colSelected > 0 )
            {
                this.colSelected-- ;
            }
            else
            {
                this.colSelected = 2 ;
            }
        }
        else if ( action == Canvas.RIGHT )
        {
            if ( this.colSelected < 2 )
            {
                this.colSelected++ ;
            }
            else
            {
                this.colSelected = 0 ;
            }
        }
        else if ( action == Canvas.UP )
        {
            if ( this.rowSelected > 0 )
            {
                this.rowSelected--   ;
            }
            else
            { 
                this.rowSelected = 2  ;
            }
        }
        else if ( action == Canvas.DOWN )
        { 
            if ( this.rowSelected < 2 )
            {
                this.rowSelected++ ;
            }
            else
            {
                this.rowSelected = 0 ;
            }
        }
        else if ( action == Canvas.FIRE )
        {
            if(this.sq[this.colSelected][this.rowSelected] == -1)
            {
                this.sq[this.colSelected][this.rowSelected] = this.userSymbol ;
                if(this.win() == false)
                {
                    this.AIRun() ;
                    this.win() ;
                }
            }
        }
        
        repaint() ;
    }
    
    private int getRandom( int min, int max ) // To copy this to other classes having this method
    {
        int lVar ;
        lVar = max - min ;
        if(lVar != 0)
        {
            lVar = (this.random.nextInt() / (Integer.MAX_VALUE / lVar)) ;
        }
        
        // For getting the absolute value
        if(lVar < 0)
        {
            lVar = -lVar ;
        }
        return ( min + lVar ) ;
    }
}
    
    