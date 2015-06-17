//
//  Quizz.swift
//  StarWars
//
//  Created by Le Tyrant Mathieu on 17/06/2015.
//  Copyright (c) 2015 BangBang. All rights reserved.
//

import Foundation
import SwiftyJSON

struct Quizz {

    /*
    ******
    * Return Dictionary with random People
    ******
    */
    static func randomPeople(callback: ([String:String]) -> Void){
        
        let randomPage:UInt32                       = arc4random_uniform(8);
        let randomPeople:UInt32                     = arc4random_uniform(9);
        var peopleInfo:Dictionary<String,String>    = Dictionary<String,String>();
        
        // PEOPLE INFORMATION
        SwApi.Peoples(randomPage, response: { (people: JSON) -> () in
            
            peopleInfo["name"]          = people["results"][Int(randomPeople)]["name"].stringValue;
            peopleInfo["eye_color"]     = people["results"][Int(randomPeople)]["eye_color"].stringValue;
            peopleInfo["height"]        = people["results"][Int(randomPeople)]["height"].stringValue;
            peopleInfo["mass"]          = people["results"][Int(randomPeople)]["mass"].stringValue;
            peopleInfo["skin_color"]    = people["results"][Int(randomPeople)]["skin_color"].stringValue;
            peopleInfo["gender"]        = people["results"][Int(randomPeople)]["gender"].stringValue;
            peopleInfo["hair_color"]    = people["results"][Int(randomPeople)]["hair_color"].stringValue;
            peopleInfo["homeworld"]     = people["results"][Int(randomPeople)]["homeworld"].stringValue;
            
            // PLANET
            SwApi.Planet(peopleInfo["homeworld"]!, response: { (planetData: JSON) -> () in
                
                peopleInfo["planet"] = planetData["name"].stringValue;
                
                // PICTURE
                GoogleImageApi.getPicture(peopleInfo["name"]!, response: { (pictures: JSON) -> () in
                    
                    peopleInfo["picture"] = pictures[0]["url"].stringValue;
                    
                    callback(peopleInfo);
                    
                });
                
            });
            
        });
        
    }
    
    static func randomAnswer(callback: (String) -> Void) {
        
        var randomPage:UInt32 = arc4random_uniform(86);
        
        
        if(Int(randomPage) == 0 || Int(randomPage) == 17) {
            randomPage = 18;
        }
        
        // PEOPLE INFORMATION
        SwApi.People(Int(randomPage), response: { (people: JSON) -> () in
                        
            let name:String = people["name"].stringValue;
            
            callback(name);
            
        });
        
    }
    
}

