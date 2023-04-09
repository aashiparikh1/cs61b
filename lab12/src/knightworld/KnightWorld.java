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

    public KnightWorld(int width, int height, int holeSize) {
        this.width = width;
        this.height = height;
        fillTiles(holeSize);
    }

    public TETile[][] fillTiles(int holeSize) {
        int rowType = 0;
        int rowTracker = holeSize;
        tiles = new TETile[height][width];
        for (int i = 0; i < height; i++) {
            if (rowType > 4) {
                rowType = 0;
            }
            int colType = 0;
            int colTracker = holeSize;
            for (int j = 0; j < width; j++) {
                if (colType > 4) {
                    colType = 0;
                }
                if (isHole(rowType, colType)) {
                   tiles[i][j] = Tileset.NOTHING;
                } else {
                    tiles[i][j] = Tileset.LOCKED_DOOR;
                }
                colTracker--;
                if (colTracker == 0) {
                    colType++;
                    colTracker = holeSize;
                }
            }
            rowTracker--;
            if (rowTracker == 0) {
                rowType++;
                rowTracker = holeSize;
            }
        }
        return tiles;
    }

    public boolean isHole(int typeOfRow, int typeOfCol) {
        if (typeOfRow == 0 && typeOfCol == 3) {
            return true;
        } else if (typeOfRow == 1 && typeOfCol == 0) {
            return true;
        } else if (typeOfRow == 2 && typeOfCol == 2) {
            return true;
        } else if (typeOfRow == 3 && typeOfCol == 4) {
            return true;
        } else if (typeOfRow == 4 && typeOfCol == 1) {
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
        int width = 60;
        int height = 40;
        int holeSize = 3;

        KnightWorld knightWorld = new KnightWorld(width, height, holeSize);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }
}
