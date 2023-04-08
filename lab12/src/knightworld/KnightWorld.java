package knightworld;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * Draws a world consisting of knight-move holes.
 */
public class KnightWorld {

    private TETile[][] tiles;
    private int width;
    private int height;
    // TODO: Add additional instance variables here

    public KnightWorld(int width, int height, int holeSize) {
        this.width = width;
        this.height = height;
        fillTiles(holeSize);
        // TODO: Fill in this constructor and class, adding helper methods and/or classes as necessary to draw the
        //  specified pattern of the given hole size for a window of size width x height. If you're stuck on how to
        //  begin, look at the provided demo code!
    }

    int rowType = 0;

    public TETile[][] fillTiles(int holeSize) {
        tiles = new TETile[width][height];
        for (int i = 0; i < width; i++) {
            if (rowType > 4) { rowType = 0;}
            for (int j = 0; j < height; j++) {
                if (isHole(rowType, j)) {
                   tiles[i][j] = Tileset.NOTHING;
                } else {
                    tiles[i][j] = Tileset.LOCKED_DOOR;
                }
            }
            rowType++;
        }
        return tiles;
    }

    public boolean isHole(int typeOfRow, int col) {
        if (typeOfRow == 0 && (col % 10 == 3 || col % 10 == 8)) {
            return true;
        } else if (typeOfRow == 1 && (col == 0 || col % 5 == 0)) {
            return true;
        } else if (typeOfRow == 2 && (col % 10 == 2 || col % 10 == 7)) {
            return true;
        } else if (typeOfRow == 3 && (col % 10 == 4 || col % 10 == 9)) {
            return true;
        } else if (typeOfRow == 4 && (col % 10 == 1 || col % 10 == 6)) {
            return true;
        }
        return false;
    }


    /** Returns the tiles associated with this KnightWorld. */
    public TETile[][] getTiles() {
        return tiles;
    }

    public static void main(String[] args) {
        // Change these parameters as necessary
        int width = 50;
        int height = 30;
        int holeSize = 2;

        KnightWorld knightWorld = new KnightWorld(width, height, holeSize);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }
}
