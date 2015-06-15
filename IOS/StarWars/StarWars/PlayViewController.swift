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
    
    @IBOutlet weak var scoreDisplay: UILabel!
    @IBOutlet weak var btnThree: UIButton!
    @IBOutlet weak var btnTwo: UIButton!
    @IBOutlet weak var btnOne: UIButton!
    
    @IBAction func btnOneAction(sender: AnyObject) { nextQuestion(1) }
    @IBAction func btnTwoAction(sender: AnyObject) { nextQuestion(2) }
    @IBAction func btnThreeAction(sender: AnyObject) { nextQuestion(3) }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(true);
        navigationController?.navigationBar.hidden = true; // for navigation bar hide
    }
    
    override func viewDidLoad() {
        super.viewDidLoad();
        self.nextQuestion(0); // Init the game
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    /*
     ******
     * Return Dictionary with random People
     ******
     */
    func randomPeople(callback: ([String:String]) -> Void){
        
        let randomPage:UInt32                       = arc4random_uniform(8);
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
                    
                    callback(peopleInfo);
                    
                });
                
            });
            
        });
        
    }
    
    /*
    ******
    * Return Dictionary with two name in random
    ******
    */
    func randomAnswer(callback: ([String]) -> Void){
        
        let randomPage:UInt32                       = arc4random_uniform(8);
        let randomPeople1:UInt32                    = arc4random_uniform(10);
        let randomPeople2:UInt32                    = arc4random_uniform(10);
        var peopleInfo                              = [String]()
        
        // PEOPLE INFORMATION
        SwApi.Peoples(randomPage, response: { (people: JSON) -> () in
            
            peopleInfo.append(people["results"][Int(randomPeople1)]["name"].stringValue);
            peopleInfo.append(people["results"][Int(randomPeople2)]["name"].stringValue);
            
            callback(peopleInfo);

        });
        
    }
    
    func endQuizz(){
        // Display the result :)
    }
    
    func nextQuestion(id:Int){
        self.score++;
        self.scoreDisplay.text = String(self.score);
        
        //        self.randomPeople {
        //            (peopleInfo) in
        //
        //            println(peopleInfo);
        //
        //            self.randomAnswer {
        //                (randomAnswer) in
        //
        //                println(randomAnswer)
        //                
        //            }
        //            
        //        }

        // TODO Display next Question :)
    }
    
}