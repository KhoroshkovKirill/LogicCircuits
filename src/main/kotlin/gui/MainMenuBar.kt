package gui

import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import Main
import javafx.event.EventHandler

class MainMenuBar(val main : Main) : MenuBar(){
    init {
        val menuFile = Menu("File")
        val menuEdit = Menu("Edit")
        val menuView = Menu("View")
        val menuRun = Menu("Run")

        /*File*/
        val itemClose = MenuItem("Close")
        itemClose.onAction = EventHandler { System.exit(1) }
        menuFile.items.add(itemClose)

        /*Edit*/

        /*View*/
        val itemTruthTable = MenuItem("Truth Table")
        itemTruthTable.onAction = EventHandler { TODO() }
        menuView.items.add(itemTruthTable)

        /*Run*/
        val itemCheck = MenuItem("Check")
        itemCheck.onAction = EventHandler { main.check() }
        menuRun.items.add(itemCheck)

        menus.addAll(menuFile, menuEdit, menuView, menuRun)
    }
}