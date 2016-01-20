/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Manager;

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
    public static List<String> namesPlayer = new ArrayList<>();
    public static List<String> namesTeam = new ArrayList<>();
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
        
        initNames();
    }
    
    public static void initNames()
    {
        JSONParser json = new JSONParser();
        
        try
        {
            Object object = json.parse(new FileReader("names.json"));
            
            JSONObject jsonObject = (JSONObject) object;

            JSONArray names = (JSONArray) jsonObject.get("joueur");
            Iterator<JSONObject> iterator = names.iterator();
            while(iterator.hasNext())
            {
                JSONObject objTemp = iterator.next();
                String plot = (String) objTemp.get("pseudo");
                Story.namesPlayer.add(plot);
            }
            JSONArray team = (JSONArray) jsonObject.get("equipes");
            iterator = team.iterator();
            while(iterator.hasNext())
            {
                JSONObject objTemp = iterator.next();
                String plot = (String) objTemp.get("name");
                Story.namesTeam.add(plot);
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
            if(Story.plot.get(i).contains("#NAMEPERSO1"))
            {
                String replaced = Story.plot.get(i).replace("#NAMEPERSO1", t.getCharacters().get(0).getName());
                Story.plot.set(i, replaced);
            }
            if(Story.plot.get(i).contains("#NAMEPERSO2"))
            {
                String replaced = Story.plot.get(i).replace("#NAMEPERSO2", t.getCharacters().get(1).getName());
                Story.plot.set(i, replaced);
            }
        }
    }
    
    public static String getRandomPlayerName()
    {
        int nbAlea = (int)(Math.random()*namesPlayer.size());
        return namesPlayer.get(nbAlea);
    }
    
    public static String getRandomTeamName()
    {
        int nbAlea = (int)(Math.random()*namesTeam.size());
        return namesTeam.get(nbAlea);
    }
}
