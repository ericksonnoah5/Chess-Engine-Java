# Chess Engine (Java)

A chess game played in the terminal, written in Java. The point of the project
was to build something non-trivial around Java's object model rather than to
produce a competitive engine.

```
[R][B][N][Q][K][N][B][R]
[P][P][P][P][P][P][P][P]
[ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ]
[ ][ ][ ][ ][P][ ][ ][ ]
[ ][ ][ ][ ][ ][ ][ ][ ]
[P][P][P][P][P][P][P][P]
[R][B][N][Q][K][N][B][R]
```

## Design

**Piece hierarchy.** `piece` is the base class holding the state every piece
shares (its display string and colour) and a `move()` method. Each piece type
in `pieces/` extends it and overrides `move()` with its own rules, so the game
loop can call `move()` on whatever is sitting on a square without knowing which
piece it is.

**Empty squares are objects, not nulls.** `nopiece` is a `piece` subclass used
for empty squares, so the board never holds a `null` and callers never need a
null check before reading a square.

**Board model.** `board` owns an 8x8 array of `square` objects, each of which
holds a `piece`. `square` also keeps a `piecememory` reference, intended for
restoring a square's previous occupant.

**Input parsing.** Moves are entered in algebraic notation (`e4`). `validate()`
rejects anything that isn't a file letter `a`-`h` followed by a rank digit
`1`-`8` and re-prompts; `decode()` then maps the notation onto array
coordinates, inverting the rank so that rank 1 is the bottom row.

## Running it

Requires a JDK (developed against Java 17).

```sh
cd terminal_game
javac -d bin $(find src -name "*.java" ! -name "module-info.java")
java -cp bin chess_game.main
```

You are prompted for a piece and a destination, one per turn:

```
Piece: e2
Where: e4
```

`q` quits, `h` shows help.

## Status

This is a work in progress, not a finished engine. Currently:

- `bishop` is the only piece with move generation implemented. It walks outward
  along each diagonal, stopping at a friendly piece and stopping after an enemy
  piece so the capture is included.
- The other piece types extend `piece` but do not yet override `move()`.
- Move legality is not enforced by the game loop, and turns do not alternate.
- There is no check, checkmate, castling, or en passant handling.

Next up: rook and queen move generation (the queen being the union of the rook
and bishop directions), then enforcing legality and turn order in the loop.
