/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author yannick
 */
public class Story
{
    public static List<String> plot = new ArrayList<>();
    private static int plotNumber=0;
    
    public static void initStory()
    {
        JSONParser json = new JSONParser();
        
        try
        {
            Object object = json.parse(new FileReader("story.json"));
            
            JSONObject jsonObject = (JSONObject) object;

            JSONArray story = (JSONArray) jsonObject.get("Story");
            Iterator<JSONObject> iterator = story.iterator();
            while(iterator.hasNext())
            {
                JSONObject objTemp = iterator.next();
                String plot = (String) objTemp.get("plot");
                Story.plot.add(plot);
            }
        } catch (Exception ex)
        {
            Logger.getLogger(Story.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getPlot()
    {
        Story.plotNumber++;
        return Story.plot.get(Story.plotNumber-1);
    }
    
    public static void replaceVars(Team t)
    {
        for(int i=0; i<Story.plot.size(); i++)
        {
            if(Story.plot.get(i).contains("#NAME"))
            {
                String replaced = Story.plot.get(i).replace("#NAME", t.getName());
                Story.plot.set(i, replaced);
            }
        }
    }
}
