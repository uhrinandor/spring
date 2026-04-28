package com.spring.prototype;

import com.spring.controllers.controllers.InitController;
import com.spring.models.utils.Tracer;
import com.spring.prototype.utils.Navigator;
import com.spring.prototype.views.IView;
import com.spring.prototype.views.InitView;


public class App 
{
    public static void main( String[] args )
    {
        Navigator navigator = new Navigator();
        // Kiindulóra mindig az Init-tel indulunk
        InitController initController = new InitController();
        InitView  initView = new InitView(navigator, initController);
        navigator.navigateTo(initView);
        
        while(true){
            IView currentView = navigator.getCurrentView();
            if(currentView == null) break;
            currentView.render();
            String input = Tracer.getInstance().askString("ENTER");
            if (input.equalsIgnoreCase("EXIT")) {
                break; 
            }

            if(input.equalsIgnoreCase("HELP")){
                currentView.showCommands();
                continue;
            }

            try{
                currentView.handle(input);
            }catch(Exception e){
                Tracer.getInstance().error("Unhandled exception occured!" + e.getMessage());
            }
            
        }
    }
}
