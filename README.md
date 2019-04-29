# a1-007-team-11
a1-007-team-11 created by GitHub Classroom

Project Name: Old Maid

Authors: Jackson Small, Chris Jung, Andrew Fields, Vihas Gowreddy

IDE: IntelliJ

Java Version: 1.8

Note: In order to compile in a version past Java 1.8, JavaFX must be properly configured. In addition, the four Java files in
      "libs" must be added to the library. Files > Project Structure > Modules > Dependencies > Add > (jsonbeans-0.5.jar, junit-4.6.jar,
      kryo-2.23.1-SNAPSHOT-all-debug.jar, minlog-none-1.2.jar)

Description: This project is a simple implementation of the classic game Old Maid. Each player is dealt an equal number of cards to their
              respective hand. The goal of the game is to form as many pairs as possible and shed them to the discard pile until no cards
              are left in the player's hand. Within the deck, a single Joker card is mixed in. The last player left will inevitable have
              this Joker card in their hand and will be unable to form a pair. This player is the loser.

Gameplay: Each player is dealt an equal, or close to equal, number of cards. Before starting the game, each player is to discard each
          matching number pair from their hand, irregardless of suit combination. Once each player has discarded all available pairs,
          the host will begin the game by choosing one card from the next player's hand to take. If a pair is made, the pair is to be
          discarded to the discard pile. The next player will then complete their turn in a similar fashion, picking from the player
          next to him or her, discarding a pair if one is available. The game will continue until only one player is remaining with the
          Joker card left. This player is the loser.
          
