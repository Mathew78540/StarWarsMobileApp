//
//  ViewController.swift
//  StarWars
//
//  Created by Le Tyrant Mathieu on 29/05/2015.
//  Copyright (c) 2015 BangBang. All rights reserved.
//

import UIKit
import SwiftyJSON

class ViewController: UIViewController {

    @IBOutlet weak var BtnPlay: UIButton!
    @IBOutlet weak var BtnHowPlay: UIButton!
    @IBOutlet weak var BtnYourResults: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        SwApi.People(1, response: { (data: JSON) -> () in
            println("====DATA FOR PEOPLE 1====");
            println(data);
        });
        
        
        GoogleImageApi.getPicture("Sky walker", response: { (data : JSON) -> () in
            println("====IMAGE FOR Sky walker ====");
            println(data[0]["url"]);
        });
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

