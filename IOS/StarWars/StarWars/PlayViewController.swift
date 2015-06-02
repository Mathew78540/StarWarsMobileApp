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
    
    let score:Int = 0;
    

    override func viewDidLoad() {
        super.viewDidLoad();
        self.initGame(); // Init the game
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func initGame(){
        let randomPage:UInt32   = arc4random_uniform(9);
        let randomPeople:UInt32 = arc4random_uniform(10);
        
        SwApi.Peoples(randomPage, response: { (data: JSON) -> () in
            
            let goodName:String             = data["results"][Int(randomPeople)]["name"].stringValue;
            let goodColoreyes:String        = data["results"][Int(randomPeople)]["eye_color"].stringValue;
            let goodSizePeople:String       = data["results"][Int(randomPeople)]["height"].stringValue;
            let goodWeightPeople:String     = data["results"][Int(randomPeople)]["mass"].stringValue;
            let goodSkinColorPeople:String  = data["results"][Int(randomPeople)]["skin_color"].stringValue;
            
            SwApi.Planet(data["results"][Int(randomPeople)]["homeworld"].stringValue, response: { (planetData: JSON) -> () in
                let goodPlanetName = planetData["name"].stringValue;
            });
            
        });
        
    }

}