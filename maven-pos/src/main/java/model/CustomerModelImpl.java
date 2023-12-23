package model;

import db.DBConnection;
import dto.CustomerDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModelImpl implements CustomerModel{
    @Override
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Customer VALUES(?,?,?,?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, customerDto.getId());
        pstm.setString(2, customerDto.getName());
        pstm.setString(3, customerDto.getAddress());
        pstm.setDouble(4, customerDto.getSalary());
        return (pstm.executeUpdate() > 0);
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE Customer SET name = ? ,address = ?, salary = ? WHERE id=?";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, customerDto.getName());
        pstm.setString(2, customerDto.getAddress());
        pstm.setDouble(3, customerDto.getSalary());
        pstm.setString(4, customerDto.getId());
        return (pstm.executeUpdate() > 0);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE from customer WHERE id=?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, id);
        int result = pstm.executeUpdate();
        return (result>0);
    }

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {

        List<CustomerDto> list = new ArrayList<>();

        String sql = "SELECT * FROM Customer";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();

        while (result.next()){
            result = pstm.getResultSet();
            CustomerDto customerDto = new CustomerDto(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4)
            );

            list.add(customerDto);
        }
        return list;
    }

    @Override
    public CustomerDto searchCustomer(String id) {
        return null;
    }
}
