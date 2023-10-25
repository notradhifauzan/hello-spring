package com.learnthepart.hellospring;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.learnthepart.hellospring.beans.Grade;
import com.learnthepart.hellospring.repository.GradeRepository;
import com.learnthepart.hellospring.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

    // use @Mock to create a mock of the Repository
    // Mock will mimic the gradeRepository dependency while having no logic of its
    // own
    // since we want to test gradeService, which depends on gradeRepository, then of
    // course
    // we would need the object of gradeRepository right?
    // "fake grade storing system"
    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks // - create object of gradeService class, inject repository mock into it
    private GradeService gradeService;

    @Test
    public void getGradesFromRepoTest() {
        // Create an array of Grade objects
        List<Grade> grades = new ArrayList<Grade>();

        grades.add(new Grade("radhi", "CSC404", 99.99));
        grades.add(new Grade("fauzan", "CSC508", 98.98));

        when(gradeRepository.getGrades()).thenReturn(grades);

        List<Grade> result = gradeService.getGrades();

        // assertEquals(expected,actual)
        assertEquals("radhi", result.get(0).getName());
        assertEquals("fauzan", result.get(1).getName());
    }

    @Test
    public void gradeIndexTest() {
        Grade gradeObj = new Grade("Munir", "STA650", 86.50);
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(gradeObj));
        when(gradeRepository.getGrade(0)).thenReturn(gradeObj);

        int valid = gradeService.getGradeIndex(gradeObj.getId());
        int notFound = gradeService.getGradeIndex("123");

        assertEquals(0, valid);
        assertEquals(Constants.NOT_FOUND, notFound);
    }

    @Test
    public void returnGradeByIdTest() {
        Grade gradeObj = new Grade("Munir", "STA650", 86.50);
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(gradeObj));
        when(gradeRepository.getGrade(0)).thenReturn(gradeObj);

        String id = gradeObj.getId();
        Grade result = gradeService.getGradeById(id);
        assertEquals(gradeObj, result);
    }

    @Test
    public void addGradeTest() {
        // in this method you will learn the 'verify assertion'
        //
        // verify(mock,times).method();
        // the mock, number of times you expect its method to be invoked
        // the method
        // we need to make sure that 'addGrade()' is called at least once

        Grade gradeObj = new Grade("Munir", "STA650", 86.50);
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(gradeObj));
        when(gradeRepository.getGrade(0)).thenReturn(gradeObj);

        Grade newGrade = new Grade("Adib", "Calculus IV", 7.00);

        // yes we know that gradeService.submitGrade is reponsible to add new grade into
        // the grade repository
        gradeService.submitGrade(newGrade);

        // however, can we confirm that gradeRepository is actually calling the addGrade
        // method at least once? or maybe 2 times?
        verify(gradeRepository, times(1)).addGrade(newGrade);
    }

    @Test
    public void updateGradeTest() {
        Grade gradeObj = new Grade("Munir", "STA650", 86.50);
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(gradeObj));
        when(gradeRepository.getGrade(0)).thenReturn(gradeObj);

        gradeObj.setScore(88.88);
        gradeService.submitGrade(gradeObj);

        verify(gradeRepository, times(1)).updateGrade(gradeObj, 0);
    }

}
