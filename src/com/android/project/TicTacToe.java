package com.android.project;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class TicTacToe extends Activity {
	gameserver gs;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        
        final ImageView iv_new_game = (ImageView) findViewById(R.id.new_game);
        final ImageView iv_options = (ImageView) findViewById(R.id.options);
        final ImageView iv_help = (ImageView) findViewById(R.id.help);
        final ImageView iv_quit = (ImageView) findViewById(R.id.quit);
        
        iv_new_game.setOnClickListener(welcome_listener);
        iv_options.setOnClickListener(welcome_listener);
        iv_help.setOnClickListener(welcome_listener);
        iv_quit.setOnClickListener(welcome_listener);
        gs=new gameserver(this);
    }
    
    /** 
     * Common onClickListener for Welcome Screen Buttons 
     * */
    OnClickListener welcome_listener = new View.OnClickListener() {
    	public void onClick(View v) {
    		final ImageView iv = (ImageView) v;
    		if (iv.getId() == R.id.new_game) {
    			showDialog(NAME_DIALOG_ID);
    		}
    		else if(iv.getId() == R.id.options) {
    			options_menu();
    		}
    		else if (iv.getId() == R.id.help) {
    			showDialog(HELP_DIALOG_ID);
    		}
    		else if (iv.getId() == R.id.quit) {
    			finish();
    		}
    	}
    };
    
    /**
     * Dialog interface for entering the name.
     */
    protected Dialog onCreateDialog(int id) {
        Dialog mdialog = new Dialog(this);
        switch(id) {
        case NAME_DIALOG_ID:
        	mdialog.setContentView(R.layout.name_dialog_2);
    		mdialog.setTitle("Player Names");
    		mdialog.setCancelable(true);
    		
    		final EditText namep1 = (EditText) mdialog.findViewById(R.id.namep1);
    		final EditText namep2 = (EditText) mdialog.findViewById(R.id.namep2);
        
    		Button ok_b = (Button) mdialog.findViewById(R.id.ok);
    		ok_b.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				change_skin();
    				player_name_2 = namep1.getText();
    				player_name_1 = namep2.getText();
    				startNewGame(namep2.getText());
    				dismissDialog(1);
    			}
    		});
            break;
            
        case HELP_DIALOG_ID :
        	mdialog.setContentView(R.layout.help);
            mdialog.setTitle("Help");
            mdialog.setCancelable(true);
        	break;
        default:
            mdialog = null;
        }
        return mdialog;
    }
    
    public void startNewGame(CharSequence player_name){
    	// reset the game view. (this must be the first line in this function)
    			setContentView (skin_layout);
    			
    	    	final ImageButton b3 = (ImageButton) findViewById(R.id.b3);
    	        final ImageButton b2 = (ImageButton) findViewById(R.id.b2);
    	        final ImageButton b1 = (ImageButton) findViewById(R.id.b1);

    	        final ImageButton b6 = (ImageButton) findViewById(R.id.b6);
    	        final ImageButton b5 = (ImageButton) findViewById(R.id.b5);
    	        final ImageButton b4 = (ImageButton) findViewById(R.id.b4);
    	        
    	        final ImageButton b9 = (ImageButton) findViewById(R.id.b9);
    	        final ImageButton b8 = (ImageButton) findViewById(R.id.b8);
    	        final ImageButton b7 = (ImageButton) findViewById(R.id.b7);
    	        
    	        // set the OnClickListeners.
    	        b1.setOnClickListener(button_listener);
    	        b2.setOnClickListener(button_listener);
    	        b3.setOnClickListener(button_listener);
    	        b4.setOnClickListener(button_listener);
    	        b5.setOnClickListener(button_listener);
    	        b6.setOnClickListener(button_listener);
    	        b7.setOnClickListener(button_listener);
    	        b8.setOnClickListener(button_listener);
    	        b9.setOnClickListener(button_listener);
    	        
    	        // Re-enable the Click-able property of buttons.
    	        b1.setClickable(true);
    	        b2.setClickable(true);
    	        b3.setClickable(true);
    	        b4.setClickable(true);
    	        b5.setClickable(true);
    	        b6.setClickable(true);
    	        b7.setClickable(true);
    	        b8.setClickable(true);
    	        b9.setClickable(true);
    	        gs.new_game();
    }
    /**
     * Creates the menu items 
     * */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_NEW_GAME, 0, "New Game");
        menu.add(0, MENU_OPTIONS, 0, "Options");
        menu.add(0, MENU_QUIT, 0, "Quit");
        return true;
    }
   
    /**
     * Handles item selections in the options menu. 
     * */
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(item.getItemId()== 0)//user wants to start a new game
    	        showDialog(NAME_DIALOG_ID);
    	else if(item.getItemId() == 1)//user wishes to see the other options available in the game
    	{	
    		// starts options menu.
    		options_menu();
	     }
    	
    	else //user wishes to quit the game
    	{
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do You Really Want to Quit?");
            builder.setCancelable(true);
              builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            	  public void onClick(DialogInterface dialog, int id) {
    					// do nothing, since setCancelable is true, canceling is enabled
    				}
            	});
              builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finish();
					
				}
			});
            AlertDialog alert = builder.create();
            alert.show();
    	}
    	return true;
    }
    
    
    // ------------------------- start of options section ----------------------------------------- //
    
    
    /** 
     * Creates an Alert Dialog for Options. 
     * */
    public void options_menu() {
    	final CharSequence[] options_items = {"Change Skin", "Choose Symbol", "Game Mode" , "Player Name", "Help", "Go Back"};
    	
    	AlertDialog.Builder options_builder = new AlertDialog.Builder(this);
    	options_builder.setTitle("Options");
    	options_builder.setItems(options_items, new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int item) {
    			if(options_items[item] == "Change Skin")
    				select_skin();
    			else if (options_items[item] == "Choose Symbol")
    				symbol_select();
    			else if(options_items[item] == "Game Mode")
    				mode_select();
    			else if(options_items[item] == "Player Name")
    				showDialog(NAME_DIALOG_ID);
    			else if (options_items[item] == "Help")
    				showDialog(HELP_DIALOG_ID);
    			else if (options_items[item] == "Go Back")
    				return;
    		}	
    	});
    	options_builder.show();
    }
    
    
    /**
     * Alert Dialog Box showing the options to select the Symbol.
     * */
    public void symbol_select() {
    	AlertDialog.Builder symbol_builder = new AlertDialog.Builder(this);
        symbol_builder.setMessage("Select Your Symbol");
        symbol_builder.setCancelable(false);
        symbol_builder.setNegativeButton("Dot", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int id) {
        		user_symbol = 0;
        		startNewGame(player_name_1);
        	}
        });
        
        symbol_builder.setPositiveButton("Cross", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				user_symbol = 1;
        		startNewGame(player_name_1); 
			}
		});
        symbol_builder.show();
    }
    
    /**
     * Alert Dialog Box showing the options to select the game mode.
     * */
    public void mode_select()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Choose your game mode : ")
        			.setPositiveButton("Vs Computer", new DialogInterface.OnClickListener() {
        				public void onClick(DialogInterface dialog, int id) {
        					Toast.makeText(getApplicationContext(), "Mode changed to Vs Computer", Toast.LENGTH_SHORT).show();
        					gs.setGame_mode(1);
        					game_mode=1;
        					
        					}
        			})
        			
        			.setNegativeButton("Vs Human", new DialogInterface.OnClickListener() {
        				public void onClick(DialogInterface dialog, int id) {
        					Toast.makeText(getApplicationContext(), "Play against your Opponent !", Toast.LENGTH_SHORT).show();
        					gs.setGame_mode(0);
        					game_mode=0;
        					
        					showDialog(NAME_DIALOG_ID);
        				}
        			});
        builder.show();
        return;
    }
    
    /**
     * Creates an Alert Dialog for selecting the Skin for the game.
     * */
    public void select_skin() {
    	final CharSequence[] skin_items = {"Neo Blue", "Sweet Pink", "Ninja" , "Crimson", "Default"};
    	
    	AlertDialog.Builder skin_builder = new AlertDialog.Builder(this);
    	skin_builder.setItems(skin_items, new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int item) {
    			Toast.makeText(getApplicationContext(), "Skin changed to " + skin_items[item], Toast.LENGTH_SHORT).show();
	         
    			if(skin_items[item] == "Neo Blue") {
    				skin = 0;
    				change_skin();
    			}
    			else if (skin_items[item] == "Sweet Pink") {
    				skin = 1;
    				change_skin();
    			}
    			else if(skin_items[item] == "Ninja") {
    				skin = 2;
    				change_skin();
    			}
    			else if (skin_items[item] == "Crimson") {
    				skin = 3;
    				change_skin();
    			}
    			else if (skin_items[item] == "Default") {
    				skin = 4;
    				change_skin();
    			}
    		}	
    	});
    	skin_builder.show();
    }
    
    /**
     * Sets the skin for the game and starts a new game with the New Skin.
     * */
    public void change_skin() {    		
    	if (skin == 1) {
    		skin_dot = R.drawable.gal_dot;
    		skin_cross = R.drawable.gal_cross;
    		skin_layout = R.layout.gal_layout;
    	}
    	else if (skin == 2) {
    		skin_dot = R.drawable.ninja_dot;
    		skin_cross = R.drawable.ninja_cross;
    		skin_layout = R.layout.ninja_layout;
    	}
    	else if (skin == 3) {
    		skin_dot = R.drawable.red_dot;
    		skin_cross = R.drawable.red_cross;
    		skin_layout = R.layout.red_layout;
    	}
    	else if (skin == 0) {
    		skin_dot = R.drawable.default_dot;
    		skin_cross = R.drawable.default_cross;
    		skin_layout = R.layout.main;
    	}
    	else if (skin == 4) {
    		skin_dot = R.drawable.system_dot;
    		skin_cross = R.drawable.system_cross;
    		skin_layout = R.layout.system_layout;
    	}
    	
    	showDialog(NAME_DIALOG_ID);
    }
    
    // ---------------------------- End of options section ---------------------------------- //
    
    // **************************** Global variables ********************************************
	int count = 0;	// to count the number of moves made.
    
    int game_mode = 1;// default 0 : h Vs h ; 1 : h Vs Comp
    int player = 1;				// sets the player no. to 1 by default.
			
  
	
	int user_symbol = 0;			// default 0: 0 to user, X to computer.
	
	int skin = 4;		// def:0; gal:1; ninja:2; red:3; system:4;
	int skin_cross = R.drawable.default_cross;	// default values.
	int skin_dot = R.drawable.default_dot;		// default values.
	int skin_layout = R.layout.main;			// default values.
	int game_bg = 2;
	
	// player names initialized with default values.
	CharSequence player_name_1 = "Player 1";
	CharSequence player_name_2 = "Player 2";
	
	
	
	// menu item numbers.
	int MENU_NEW_GAME = 0;
    int MENU_OPTIONS = 1;
    int MENU_QUIT = 2;
    
    // dialog IDs
    final int NAME_DIALOG_ID = 1;
    final int HELP_DIALOG_ID = 2;
	
	//************************ End of Global Variable Declaration ********************************** 
	
	/**
	 * Common onClickListener for all the ImageButtons in the Game. 
	 * */
    OnClickListener button_listener = new View.OnClickListener() {
        public void onClick(View v) {
            ImageButton ibutton = (ImageButton) v;
        	
        	// Button inactive for further clicks until a result is obtained.
        	ibutton.setClickable(false);
        	
        	// Increment Count on clicking the button.
        	count=gs.incrementCount();
        	
            if ((count % 2 != 0) && (game_mode == 0)) {
            	gs.setPlayer(1);
                ibutton.setImageResource(skin_cross);
            }
            else if ((count % 2 == 0) || (game_mode == 1)) {
            	gs.setPlayer(2);
            	player = 2;			// human player.
            	if ((user_symbol == 0) && (game_mode == 1))
            		ibutton.setImageResource(skin_dot);
            	else if ((user_symbol == 1) && (game_mode == 1))
            		ibutton.setImageResource(skin_cross);
            	else
            		ibutton.setImageResource(skin_dot);
            }
            
            // after_move function to check the result and decide.
        	gs.after_move((CharSequence)ibutton.getTag());
        }
    };
    
    
    public boolean show_result(CharSequence message)		//function to select the game mode
    {   
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
        			.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
        				public void onClick(DialogInterface dialog, int id) {
        		    		// reset the game environment.
        						startNewGame(player_name_1);
        				}
        			});
        AlertDialog alert = builder.create();
        alert.show();
        return true;
    }
    
    
    /**
	 * Make the computer's move.
	 * @param x : the x co-ordinate of the move to made.
	 * @param y : the y co-ordinate of the move to made.
	 */
    public void comp_play (int x, int y) {
       	final ImageButton ib_tmp = (ImageButton) findViewById(R.id.b1);
       	int ib_id = ib_tmp.getId();		// initialize with 1st button's id.
       	
       	// set ib_id to exact ImageButton Id
       	if ((x == 0) && (y == 0)) {	
       		// ib_id same as initialized value.
       	}
       	else {
       		if (x == 0)
       			ib_id -= y;			// minus '-' : because id number not in proper order.
       		else if (x == 1)
       			ib_id += (3 - y);
       		else if (x == 2)
       			ib_id += (6 - y);	
       	}
       	
       	// bind new ib_id Image Button to variable ib.
       	final ImageButton ib = (ImageButton) findViewById (ib_id);
       	
       	// draw the symbol on the button
       	if (user_symbol == 0)
       		ib.setImageResource(skin_cross);
       	else
       		ib.setImageResource(skin_dot);
       	
       	// make the button un-clickable.
       	ib.setClickable(false);
   
       	// call the after_move function with the arguments.
       	gs.after_move((CharSequence)ib.getTag());
    }
    
    
    public void updateScore(int score1, int score2){
    	TextView tv = (TextView) findViewById(R.id.scoreboard);

    	if (game_mode == 1) {
    		player_name_1 = "Computer";
    	}
    		
    	CharSequence score_txt = 
    		player_name_1 + " : " + score1 + "                   " + player_name_2 + " : " + score2;
    	
    	tv.setText(score_txt);
    }
}