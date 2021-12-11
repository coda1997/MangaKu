//
//  DetailViewModel.swift
//  iosApp
//
//  Created by Uwais Alqadri on 14/09/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import Foundation
import KotlinCore
import KMPNativeCoroutinesCombine
import Combine

class DetailViewModel: ObservableObject {

  @Published var manga: Manga?
  @Published var loading = false
  @Published var errorMessage = ""

  private let detailUseCase: DetailUseCase
  private var cancellables = Set<AnyCancellable>()

  init(detailUseCase: DetailUseCase) {
    self.detailUseCase = detailUseCase
  }

  func fetchManga(mangaId: String) {
    loading = true
    createPublisher(for: detailUseCase.getDetailMangaNative(id: mangaId))
      .receive(on: DispatchQueue.main)
      .sink { completion in
        switch completion {
        case .finished:
          self.loading = false
        case .failure(let error):
          self.errorMessage = error.localizedDescription
        }
      } receiveValue: { value in
        self.manga = value
      }.store(in: &cancellables)
  }

}
