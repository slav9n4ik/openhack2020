//
//  WaterMetersNetworkManager.swift
//  OpenApp
//
//  Created by Вячеслав Яшунин on 23.10.2020.
//

import Foundation

final class WaterMetersNetworkManagerImpl: WaterMetersNetworkManager {
    
    private let _baseManager: NetworkManager
    private let _urlManager: WaterMetersURLManagerProtocol
    
    init(baseManager: NetworkManager, urlManager: WaterMetersURLManagerProtocol) {
        self._baseManager = baseManager
        self._urlManager = urlManager
    }
    
    func loadGlobalModels(imageData: Data, completion: @escaping (NetworkResult<WaterMetersResponse, NetworkResponseError>) -> ()) {
        let url = _urlManager.getUploadImageURL()
        let boundary = UUID().uuidString
        let request = BaseURLRequest(url: url,
                                     httpMethod: .POST,
                                     timeoutInterval: 30,
                                     contentType: .formData(boundary: boundary))
        
        var httpBody = Data()
        
        httpBody.appendStringData(convertFormField(named: "type", value: "WATER", using: boundary))
        
        httpBody.append(convertFileData(fieldName: "photo",
                                        fileName: "watermeter.jpeg",
                                        mimeType: "image/png",
                                        fileData: imageData,
                                        using: boundary))
        
        _baseManager.makeUploadRequest(with: request.rawRequest, uploadingData: httpBody, decode: { json -> WaterMetersResponse? in
            guard let decodedModel = json as? WaterMetersResponse else { return nil }
            return decodedModel
        }, completion: completion)
    }
    
    
    //MARK: - Private methods
    private func convertFileData(fieldName: String, fileName: String, mimeType: String, fileData: Data, using boundary: String) -> Data {
      var data = Data()

      data.appendStringData("--\(boundary)\r\n")
      data.appendStringData("Content-Disposition: form-data; name=\"\(fieldName)\"; filename=\"\(fileName)\"\r\n")
      data.appendStringData("Content-Type: \(mimeType)\r\n\r\n")
      data.append(fileData)
      data.appendStringData("\r\n")

      return data
    }
    
    private func convertFormField(named name: String, value: String, using boundary: String) -> String {
      var fieldString = "--\(boundary)\r\n"
      fieldString += "Content-Disposition: form-data; name=\"\(name)\"\r\n"
      fieldString += "\r\n"
      fieldString += "\(value)\r\n"

      return fieldString
    }
}
