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

        /*Run*/
        val itemCheck = MenuItem("Check")
        itemCheck.onAction = EventHandler { main.check() }
        menuRun.items.add(itemCheck)

        menus.addAll(menuFile, menuEdit, menuView, menuRun)
    }
}