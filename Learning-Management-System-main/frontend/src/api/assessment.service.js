import api from "./api";

async function getQuestions(courseId) {
  try {
    console.log("Fetching questions for courseId:", courseId);
    const { data } = await api.get(`/api/questions/course/${courseId}`);
    console.log("Questions response:", data);
    if (data && Array.isArray(data)) {
    return { success: true, data };
    } else {
      console.warn("Invalid data format received:", data);
      return { success: false, error: "Invalid data format", data: [] };
    }
  } catch (err) {
    console.error("Error fetching questions:", err);
    console.error("Error response:", err.response?.data);
    return { success: false, error: err.response?.data?.message || "Unable to fetch questions", data: [] };
  }
}

async function submitAssessment(userId, courseId, marks) {
  try {
    const payload = { courseId, userId, marks };
    const { data } = await api.post(`/api/assessments/add/${userId}/${courseId}`, payload);
    return { success: true, data };
  } catch (err) {
    console.error("Error submitting assessment:", err);
    return { success: false, error: "Unable to submit assessment" };
  }
}

export const assessmentService = {
  getQuestions,
  submitAssessment,
};
