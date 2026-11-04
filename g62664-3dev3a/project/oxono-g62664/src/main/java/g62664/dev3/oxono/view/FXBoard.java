package g62664.dev3.oxono.view;

import g62664.dev3.oxono.model.*;
import g62664.dev3.oxono.controller.FXController;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

public class FXBoard extends StackPane {
    // attributes
    private final GridPane gridPane;
    private FXController controller;

    // constructor
    public FXBoard(int size) {
        this.gridPane = createBoardGrid(size);
    }

    // getter
    public GridPane getGridPane() {
        return gridPane;
    }

    // setter
    public void setController(FXController controller) {
        this.controller = controller;
    }

    // methods
    public GridPane createBoardGrid(int size) {
        GridPane grid = new GridPane();
        grid.setPrefSize(500, 500);
        grid.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        grid.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        BackgroundImage backgroundImage = getBackgroundImage(size);

        grid.setBackground(new Background(backgroundImage));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(3);
        grid.setVgap(3);

        return grid;
    }

    private static BackgroundImage getBackgroundImage(int size) {
        BackgroundImage backgroundImage = null;

        if (size == 6) {
            backgroundImage = new BackgroundImage(
                    Images.BOARD_BACKGROUND_6x6,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(500, 500, false, false, false, false)
            );
        } else if (size == 8) {
            backgroundImage = new BackgroundImage(
                    Images.BOARD_BACKGROUND_8x8,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(500, 500, false, false, false, false)
            );
        }
        return backgroundImage;
    }

    public void updateBoard(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("Board can't be null");
        }

        gridPane.getChildren().clear();

        for (int row = 0; row < board.getBoardSize(); row++) {
            for (int col = 0; col < board.getBoardSize(); col++) {
                StackPane cell = createCell(board, row, col);
                gridPane.add(cell, col, row);
            }
        }
    }
    public StackPane createCell(Board board, int row, int col) {
        StackPane cell = new StackPane();
        cell.setAlignment(Pos.CENTER);
        cell.setMaxWidth(63);
        cell.setMaxHeight(63);

        Piece piece = board.getPieceAt(row, col);
        ImageView pieceImage = getImageForPiece(piece);

        if (pieceImage != null) {
            pieceImage.setFitWidth(63);
            pieceImage.setFitHeight(63);
            cell.getChildren().add(pieceImage);
        } else {
            Rectangle rect = new Rectangle();
            rect.setWidth(63);
            rect.setHeight(63);
            rect.setFill(javafx.scene.paint.Color.TRANSPARENT);
            cell.getChildren().add(rect);
        }

        cell.setOnMouseClicked(e -> {
            if (controller != null) {
                controller.handleGridClick(row, col);
            }
        });

        return cell;
    }
    public ImageView getImageForPiece(Piece piece) {
        if (piece == null) {
            return new ImageView(Images.EMPTY_BOX);
        } else if (piece instanceof Totem totem) {
            if (totem.getSymbol() == Symbol.O) {
                return new ImageView(Images.TOTEM_O);
            } else if (totem.getSymbol() == Symbol.X) {
                return new ImageView(Images.TOTEM_X);
            }
        } else {
            if (piece.getSymbol() == Symbol.O) {
                if (piece.getColor() == Color.PINK) {
                    return new ImageView(Images.PINK_O);
                } else {
                    return new ImageView(Images.BLACK_O);
                }
            } else if (piece.getSymbol() == Symbol.X) {
                if (piece.getColor() == Color.PINK) {
                    return new ImageView(Images.PINK_X);
                } else {
                    return new ImageView(Images.BLACK_X);
                }
            }
        }

        return null;
    }
    public void displayBoardInConsole(Board board) {
        String boardString = "";
        boardString = boardString.concat("  1 2 3 4 5 6");
        boardString = boardString.concat("\n");

        for (int i = 0; i < 6; i++) {
            boardString = boardString.concat((i+1) + " ");

            for (int j = 0; j < 6; j++) {
                if (board.isEmpty(i, j)) {
                    boardString = boardString.concat("  ");

                } else if (board.getPieceAt(i, j).getSymbol() == Symbol.O) {
                    if (board.getPieceAt(i, j).getColor() == Color.BLUE) {
                        boardString = boardString.concat("\u001B[36m" + "O " + "\u001B[0m");
                    } else if (board.getPieceAt(i, j).getColor() == Color.PINK) {
                        boardString = boardString.concat("\u001B[35m" + "O " + "\u001B[0m");
                    } else {
                        boardString = boardString.concat("O ");
                    }

                } else if (board.getPieceAt(i, j).getSymbol() == Symbol.X) {
                    if (board.getPieceAt(i, j).getColor() == Color.BLUE) {
                        boardString = boardString.concat("\u001B[36m" + "X " + "\u001B[0m");
                    } else if (board.getPieceAt(i, j).getColor() == Color.PINK) {
                        boardString = boardString.concat("\u001B[35m" + "X " + "\u001B[0m");
                    } else {
                        boardString = boardString.concat("X ");
                    }
                }
            }

            boardString = boardString.concat("\n");
        }

        System.out.println(boardString);
    }
    // @TODO fix this method (doesn't show anything)
    public void showAvailablePositions(int[][] positionsAvailable) {
        System.out.println("in the 'showAvailablePositions'");

        for (int[] position : positionsAvailable) {
            StackPane curBox = (StackPane) getBoxFromGridPane(gridPane, position[0], position[1]);
            curBox.getChildren().add(new ImageView(Images.AVAILABLE_BOX));

            System.out.println("Updating position: [" + position[0] + ", " + position[1] + "]");
            Platform.runLater(() -> {
                System.out.println("après set for position: [" + position[0] + ", " + position[1] + "]");
            });
        }
    }
    public Node getBoxFromGridPane(GridPane gridPane, int col, int row) {
        System.out.println("in the 'getBoxFromGridPane'");
        for (Node box : gridPane.getChildren()) {
            Integer colIndex = GridPane.getColumnIndex(box);
            Integer rowIndex = GridPane.getRowIndex(box);
            System.out.println(box);

            if (colIndex != null && rowIndex != null && colIndex == col && rowIndex == row) {
                return box;
            }
        }
        return null;
    }
}
