//
//  MangaKuApp.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 Uwais Alqadri. All rights reserved.
//

import SwiftUI
import KotlinCore

@main
struct MangaKuApp: App {

  private let assembler = AppAssembler()

  var body: some Scene {
    WindowGroup {
      TabNavigationView(assembler: assembler)
        .onAppear {
          CoreModuleKt.doInitKoin()
        }
    }
  }
}
