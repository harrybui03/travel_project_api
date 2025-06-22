package com.example.demo.repository;

import com.example.demo.entity.CustomerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment, Long> {

    // Thống kê doanh thu theo tháng trong năm
    @Query("SELECT EXTRACT(MONTH FROM cp.paymentDate) as month, SUM(cp.paymentAmount) " +
            "FROM CustomerPayment cp WHERE EXTRACT(YEAR FROM cp.paymentDate) = ?1 " +
            "GROUP BY EXTRACT(MONTH FROM cp.paymentDate) " +
            "ORDER BY EXTRACT(MONTH FROM cp.paymentDate)")
    List<Object[]> getRevenueByMonth(int year);

    // Thống kê doanh thu theo quý trong năm
    @Query("SELECT FLOOR((EXTRACT(MONTH FROM cp.paymentDate) - 1) / 3) + 1 as quarter, SUM(cp.paymentAmount) " +
            "FROM CustomerPayment cp WHERE EXTRACT(YEAR FROM cp.paymentDate) = ?1 " +
            "GROUP BY FLOOR((EXTRACT(MONTH FROM cp.paymentDate) - 1) / 3) + 1 " +
            "ORDER BY FLOOR((EXTRACT(MONTH FROM cp.paymentDate) - 1) / 3) + 1")
    List<Object[]> getRevenueByQuarter(int year);

    // Thống kê doanh thu theo năm
    @Query("SELECT EXTRACT(YEAR FROM cp.paymentDate) as year, SUM(cp.paymentAmount) " +
            "FROM CustomerPayment cp GROUP BY EXTRACT(YEAR FROM cp.paymentDate) " +
            "ORDER BY EXTRACT(YEAR FROM cp.paymentDate)")
    List<Object[]> getRevenueByYear();

    // Thống kê chi tiết theo tháng trong năm (native query)
    @Query(value = """
            SELECT 
                EXTRACT(MONTH FROM cp.payment_date) AS month, 
                COUNT(cp.id) AS transactionCount,
                COUNT(DISTINCT cp.customer_id) AS customerCount,
                json_agg(
                    json_build_object(
                        'id', cp.id,
                        'amount', cp.payment_amount,
                        'method', cp.payment_method,
                        'date', cp.payment_date,
                        'tax', cp.tax,
                        'discount', cp.discount,
                        'customerId', cp.customer_id,
                        'employeeId', cp.employee_id,
                        'tourBookingId', cp.tour_booking_id
                    )
                ) AS transactions,
                SUM(cp.payment_amount) AS totalRevenue
            FROM customer_payment cp 
            WHERE EXTRACT(YEAR FROM cp.payment_date) = ?1
            GROUP BY EXTRACT(MONTH FROM cp.payment_date)
            ORDER BY EXTRACT(MONTH FROM cp.payment_date)
            """, nativeQuery = true)
    List<Object[]> getDetailedRevenueByMonth(int year);

}
