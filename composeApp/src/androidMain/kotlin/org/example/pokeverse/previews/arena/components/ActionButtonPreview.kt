package org.example.pokeverse.previews.arena.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.pokeverse.arena.data.Action
import org.example.pokeverse.arena.presentation.ActionButton

@Preview
@Composable
private fun ActionButtonPreview() {
    ActionButton(
        action =
        Action.Attack(
            timestamp = System.currentTimeMillis().toString(),
            image = "https://static.vecteezy.com/system/resources/previews/023/364/242/original/yellow-ball-lightning-abstract-electric-lightning-strike-light-flash-thunder-spark-png.png",
            metaData = Action.MetaData(primaryColor = 0xFFFCA503, secondaryColor = 0xFFED5407),
            damage = 20f,
            title = "Sworm"
        ),
        isActive = true,
        onPerform = {}
    )
}
