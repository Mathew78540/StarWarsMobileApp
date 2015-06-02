//
//  PlayViewController.swift
//  StarWars
//
//  Created by Le Tyrant Mathieu on 01/06/2015.
//  Copyright (c) 2015 BangBang. All rights reserved.
//

import UIKit
import SwiftyJSON

class PlayViewController: UIViewController {
    
    var score:Int = 0;
    
    override func viewDidLoad() {
        super.viewDidLoad();
        self.initGame(); // Init the game
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func initGame(){
        let randomPage:UInt32                       = arc4random_uniform(9);
        let randomPeople:UInt32                     = arc4random_uniform(10);
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
                    
                    println(peopleInfo);
                    
                });
                
            });
            
        });
        
    }
    
}