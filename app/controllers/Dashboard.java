package controllers;

import models.Assessment;
import models.AssessmentList;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");

    Assessment a1 = new Assessment(60.2, 45.0, 12.2, 44.4, 38,38.0);
    Assessment a2 = new Assessment(56.6, 23.4, 24.5, 23.4, 45.2,22.4);

    AssessmentList assessmentList = new AssessmentList("Assessment For Me");
    assessmentList.assessments.add(a1);
    assessmentList.assessments.add(a2);

    render ("dashboard.html", assessmentList);
  }
}
