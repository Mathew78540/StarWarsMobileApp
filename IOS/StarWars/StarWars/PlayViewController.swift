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
    
    let peoplePerPage   = 10;
    let peopleCount     = 86;

    override func viewDidLoad() {
        super.viewDidLoad();
        self.initGame(); // Init the game
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func initGame(){
        
//        let totalPages:Int  = ceil(self.peopleCount / self.peoplePerPage); // TODO Fix it
//        let randomPeople    = arc4random_uniform(peopleCount);
        
    }

}

//        SwApi.People(1, response: { (data: JSON) -> () in
//            println("====DATA FOR PEOPLE 1====");
//            println(data);
//        });
//
//
//        GoogleImageApi.getPicture("Sky walker", response: { (data : JSON) -> () in
//            println("====IMAGE FOR Sky walker ====");
//            println(data[0]["url"]);
//        });
