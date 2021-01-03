package be.inniger.advent

object Day13 {

    fun solveFirst(tracks: List<String>): String {
        val grid = Grid.of(tracks)
        val carts = readCarts(tracks)

        while (true) {
            val moveOrder = carts.keys.sortedWith(compareBy({ it.y }, { it.x }))

            for (position in moveOrder) {
                val cart = carts.remove(position)!!
                val track = grid.tracks[position]!!

                val (newCart, newPosition) = moveCart(cart, position, track)

                if (carts.containsKey(newPosition)) {
                    return newPosition.toString()
                }

                carts[newPosition] = newCart
            }
        }
    }

    fun solveSecond(tracks: List<String>): String {
        val grid = Grid.of(tracks)
        val carts = readCarts(tracks)

        while (true) {
            if (carts.size == 1) {
                return carts.keys.single().toString()
            }

            val moveOrder = carts.keys.sortedWith(compareBy({ it.y }, { it.x }))

            for (position in moveOrder) {
                val cart = carts.remove(position)
                if (cart != null) {
                    val track = grid.tracks[position]!!

                    val (newCart, newPosition) = moveCart(cart, position, track)

                    if (carts.containsKey(newPosition)) {
                        carts.remove(newPosition)
                    } else {
                        carts[newPosition] = newCart
                    }
                }
            }
        }
    }

    private fun readCarts(tracks: List<String>): MutableMap<Coordinate, Cart> {
        return tracks.flatMapIndexed { y, tracksString ->
            tracksString.toCharArray().mapIndexed { x, track ->
                Coordinate(x, y) to Cart.of(track)
            }
        }
            .filter { it.second != null }
            .toMap()
            .mapValues { it.value!! }
            .toMutableMap()
    }

    private fun moveCart(cart: Cart, position: Coordinate, track: Track) =
        when (track) {
            Track.V -> {
                when (cart.direction) {
                    Cart.Direction.U -> cart to Coordinate(position.x, position.y - 1)
                    Cart.Direction.D -> cart to Coordinate(position.x, position.y + 1)
                    else -> error("Invalid direction for cart $cart on track $track and position $position")
                }
            }
            Track.H -> {
                when (cart.direction) {
                    Cart.Direction.R -> cart to Coordinate(position.x + 1, position.y)
                    Cart.Direction.L -> cart to Coordinate(position.x - 1, position.y)
                    else -> error("Invalid direction for cart $cart on track $track and position $position")
                }
            }
            Track.R -> when (cart.direction) {
                Cart.Direction.U -> Cart(Cart.Direction.R, cart.turn) to Coordinate(position.x + 1, position.y)
                Cart.Direction.R -> Cart(Cart.Direction.U, cart.turn) to Coordinate(position.x, position.y - 1)
                Cart.Direction.D -> Cart(Cart.Direction.L, cart.turn) to Coordinate(position.x - 1, position.y)
                Cart.Direction.L -> Cart(Cart.Direction.D, cart.turn) to Coordinate(position.x, position.y + 1)
            }
            Track.L -> when (cart.direction) {
                Cart.Direction.U -> Cart(Cart.Direction.L, cart.turn) to Coordinate(position.x - 1, position.y)
                Cart.Direction.R -> Cart(Cart.Direction.D, cart.turn) to Coordinate(position.x, position.y + 1)
                Cart.Direction.D -> Cart(Cart.Direction.R, cart.turn) to Coordinate(position.x + 1, position.y)
                Cart.Direction.L -> Cart(Cart.Direction.U, cart.turn) to Coordinate(position.x, position.y - 1)
            }
            Track.C -> {
                val newCart = cart.turnCrossRoad()
                when (newCart.direction) {
                    Cart.Direction.U -> newCart to Coordinate(position.x, position.y - 1)
                    Cart.Direction.R -> newCart to Coordinate(position.x + 1, position.y)
                    Cart.Direction.D -> newCart to Coordinate(position.x, position.y + 1)
                    Cart.Direction.L -> newCart to Coordinate(position.x - 1, position.y)
                }
            }
        }

//    private fun toAscii(grid: Grid, carts: Map<Coordinate, Cart>) =
//        (0 until grid.height).joinToString("\n") { y ->
//            (0 until grid.width)
//                .map { x -> Coordinate(x, y) }
//                .joinToString("") { coordinate ->
//                    when {
//                        carts.containsKey(coordinate) -> carts[coordinate]!!.toString()
//                        grid.tracks[coordinate] == null -> " "
//                        else -> grid.tracks[coordinate]!!.toString()
//                    }
//                }
//        }

    private enum class Track(val printValue: Char, private vararg val aliases: Char) {
        V('|', '^', 'v'),
        H('-', '<', '>'),
        R('/'),
        L('\\'),
        C('+');

        companion object {
            fun of(c: Char) = values().singleOrNull { it.printValue == c || it.aliases.contains(c) }
        }

        override fun toString() = printValue.toString()
    }

    private data class Grid(val tracks: Map<Coordinate, Track>, val height: Int, val width: Int) {

        companion object {
            fun of(tracks: List<String>) = Grid(
                tracks.flatMapIndexed { y, tracksString ->
                    tracksString.toCharArray().mapIndexed { x, track ->
                        Coordinate(x, y) to Track.of(track)
                    }
                }
                    .filter { it.second != null }
                    .toMap()
                    .mapValues { it.value!! },
                tracks.size,
                tracks.map { it.length }.maxOrNull()!!
            )
        }
    }

    private data class Cart(val direction: Direction, val turn: Turn = Turn.L) {

        companion object {
            fun of(c: Char): Cart? {
                val direction = Direction.values().singleOrNull { c == it.ascii }
                return if (direction != null) Cart(direction) else null
            }
        }

        enum class Direction(val ascii: Char) {
            U('^'),
            R('>'),
            D('v'),
            L('<');
        }

        private enum class Turn {
            L, S, R
        }

        fun turnCrossRoad() = Cart(
            when (turn) {
                Turn.L -> when (direction) {
                    Direction.U -> Direction.L
                    Direction.R -> Direction.U
                    Direction.D -> Direction.R
                    Direction.L -> Direction.D
                }
                Turn.S -> direction
                Turn.R -> when (direction) {
                    Direction.U -> Direction.R
                    Direction.R -> Direction.D
                    Direction.D -> Direction.L
                    Direction.L -> Direction.U
                }
            },
            when (turn) {
                Turn.L -> Turn.S
                Turn.S -> Turn.R
                Turn.R -> Turn.L
            }
        )

        override fun toString() = direction.ascii.toString()
    }

    private data class Coordinate(val x: Int, val y: Int) {

        override fun toString() = "$x,$y"
    }
}
