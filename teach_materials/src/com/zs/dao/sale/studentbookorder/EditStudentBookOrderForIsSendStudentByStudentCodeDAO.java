package com.zs.dao.sale.studentbookorder;

import com.feinno.framework.common.dao.jpa.EntityJpaDao;
import com.zs.domain.sale.StudentBookOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Allen on 2016/9/19.
 */
public interface EditStudentBookOrderForIsSendStudentByStudentCodeDAO extends EntityJpaDao<StudentBookOrder, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "update student_book_order set is_send_student = ?1 where student_code = ?2 and state < 2")
    public void edit(int isSendStudent, String studentCode);

    @Modifying
    @Query(nativeQuery = true, value = "update student_book_order set send_address = ?1, send_phone = ?2, send_zip_code = ?3 where student_code = ?4 and state < 2")
    public void editSendInfo(String address, String phone, String zipCode, String studentCode);
}
