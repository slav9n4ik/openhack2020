//
//  AppDelegate.swift
//  OpenApp
//
//  Created by Вячеслав Яшунин on 23.10.2020.
//

import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        window = UIWindow(frame: UIScreen.main.bounds)
        let storyboard = UIStoryboard(name: "WaterMeterCamera", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: "WaterMeterCameraController")
        window?.rootViewController = vc
        window?.makeKeyAndVisible()
        return true
    }
}

