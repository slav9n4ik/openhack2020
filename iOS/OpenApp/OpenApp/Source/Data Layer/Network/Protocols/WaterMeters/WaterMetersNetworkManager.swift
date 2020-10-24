//
//  WaterMetersNetworkManager.swift
//  OpenApp
//
//  Created by Вячеслав Яшунин on 23.10.2020.
//

import Foundation

protocol WaterMetersNetworkManager {
    init(baseManager: NetworkManager, urlManager: WaterMetersURLManagerProtocol)
    func loadGlobalModels(imageData: Data, completion: @escaping (NetworkResult<WaterMetersResponse, NetworkResponseError>) -> ())
}
