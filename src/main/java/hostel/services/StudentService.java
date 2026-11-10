package hostel.services;

import hostel.models.Student;
import hostel.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> getPaginatedStudents(int pageNumber, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                    ? Sort.by(sortField).ascending() 
                    : Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort); 
        return studentRepository.findAll(pageable);
    }
}