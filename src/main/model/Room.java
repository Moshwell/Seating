package main.model;

public class Room {

    private Integer id;

    private String roomName;

    private Integer nbRows;
    
    private Integer nbCols;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRows() {
        return nbRows;
    }

    public void setRows(Integer nbRows) {
        this.nbRows = nbRows;
    }
    
    public Integer getCols() {
        return nbCols;
    }

    public void setCols(Integer nbCols) {
        this.nbCols = nbCols;
    }
}
