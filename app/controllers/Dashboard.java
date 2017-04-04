package controllers;

import models.Assessment;
import models.AssessmentList;
import play.Logger;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");

    Assessment a1 = new Assessment(60.2, 45.0, 12.2, 44.4, 38,38.0);
    Assessment a2 = new Assessment(56.6, 23.4, 24.5, 23.4, 45.2,22.4);

    AssessmentList al1 = new AssessmentList("Assessment For Me");
    al1.assessments.add(a1);

    AssessmentList al2 = new AssessmentList("Assessment For You");
    al2.assessments.add(a2);

    List<AssessmentList> assessmentLists = new ArrayList<AssessmentList>();
    assessmentLists.add(al1);
    assessmentLists.add(al2);

    render ("dashboard.html", assessmentLists);
  }
}
