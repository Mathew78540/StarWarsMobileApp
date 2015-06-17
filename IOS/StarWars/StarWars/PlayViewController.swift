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
    
    // BTN OUTLET
    @IBOutlet weak var btnThree: UIButton!
    @IBOutlet weak var btnTwo: UIButton!
    @IBOutlet weak var btnOne: UIButton!
    
    // CLUE OUTLET
    @IBOutlet weak var clueGender: UILabel!
    @IBOutlet weak var clueEyes: UILabel!
    @IBOutlet weak var clueSize: UILabel!
    @IBOutlet weak var clueHair: UILabel!
    @IBOutlet weak var clueFace: UILabel!
    @IBOutlet weak var cluePlanet: UILabel!
    
    @IBAction func btnOneAction(sender: AnyObject) { }
    @IBAction func btnTwoAction(sender: AnyObject) { }
    @IBAction func btnThreeAction(sender: AnyObject) { }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(true);
        navigationController?.navigationBar.hidden = true; // for navigation bar hide
    }
    
    override func viewDidLoad() {
        super.viewDidLoad();
        self.nextQuestion(); // Init the game
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // Next Question
    func nextQuestion(){
        self.scoreDisplay.text = String(self.score);
        
        Quizz.randomPeople {
            (peopleInfo) in
            self.displayClues(peopleInfo);
            
            Quizz.randomAnswer {
                (name_1) in
                
                Quizz.randomAnswer {
                    (name_2) in
                    
                    self.displayAnswers(peopleInfo, name1: name_1, name2: name_2);
                }
            }
        }
    }
    
    // Reset Clues Text and Btn Title
    func resetCluesAndAnswers(){
        
        let loadingText:String = "NC";
        
        self.clueGender.text    = loadingText;
        self.clueEyes.text      = loadingText;
        self.clueSize.text      = loadingText;
        self.clueHair.text      = loadingText;
        self.clueFace.text      = loadingText;
        self.cluePlanet.text    = loadingText;
        
        self.btnOne.setTitle(loadingText, forState: UIControlState.Normal);
        self.btnTwo.setTitle(loadingText, forState: UIControlState.Normal);
        self.btnThree.setTitle(loadingText, forState: UIControlState.Normal);

    }
    
    // Display Clues
    func displayClues(clues:[String:String]){
        
        self.clueGender.text    = clues["gender"];
        self.clueEyes.text      = clues["eye_color"];
        self.clueSize.text      = clues["height"];
        self.clueHair.text      = clues["hair_color"];
        self.clueFace.text      = clues["skin_color"];
        self.cluePlanet.text    = clues["planet"];
        
    }
    
    // Display Answers
    func displayAnswers(gAnswers:[String:String], name1:String, name2:String){
        self.btnOne.setTitle(gAnswers["name"], forState: UIControlState.Normal);
        self.btnTwo.setTitle(name1, forState: UIControlState.Normal);
        self.btnThree.setTitle(name2, forState: UIControlState.Normal);
    }
    
}