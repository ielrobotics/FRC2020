import cv2
cap = cv2.VideoCapture(0)
success , image = cap.read()
while True:
	cv2.imshow("cap",cap)
	if cv2.waitkey(1) & 0xFF == ord("q"):
		break
cap.relase()
cv2.destroyAllWindows()

