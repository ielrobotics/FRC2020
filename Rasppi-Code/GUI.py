import numpy as np
import cv2
import time
cap = cv2.VideoCapture(0)
def nothing(x):
  pass
def _color_framing(frames,l_h,l_s,l_v,u_h,u_s,u_v):
    frame=frames
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    lower=np.array([l_h,l_s,l_v])
    upper=np.array([u_h,u_s,u_v])
    mask=cv2.inRange(hsv,lower,upper)
    frame=cv2.bitwise_and(frame,frame,mask=mask)
    kernel = np.ones((5,5), np.uint8) 
    frame=cv2.erode(frame, kernel, iterations=1)
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    blur = cv2.GaussianBlur(gray,(5,5),0)
    _, thresh_img = cv2.threshold(blur,91,255,cv2.THRESH_BINARY)
    return thresh_img
def detectocta(frame,l_h,l_s,l_v,u_h,u_s,u_v):
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    lower=np.array([l_h,l_s,l_v])
    upper=np.array([u_h,u_s,u_v])
    mask=cv2.inRange(hsv,lower,upper)
    band=cv2.bitwise_and(frame,frame,mask=mask)
    cv2.imshow("mask", band)
    kernel = np.ones((5,5), np.uint8)
    erode=cv2.erode(band, kernel, iterations=1)
    cv2.imshow("erode", band)
    bgr = cv2.cvtColor(erode, cv2.COLOR_HSV2BGR)
    gray = cv2.cvtColor(bgr, cv2.COLOR_BGR2GRAY)
    blur = cv2.GaussianBlur(gray,(5,5),0)
    _, thresh_img = cv2.threshold(blur,91,255,cv2.THRESH_BINARY)
    h, w = thresh_img.shape
    conres = cv2.findContours(blur, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    contours = conres[1]
    
    for cont  in contours:
        area = cv2.contourArea(cont)
        if area > 400:
            approx = cv2.approxPolyDP(cont, 0.009 * cv2.arcLength(cont, True), True)
            if (len(approx) == 8):
                cv2.drawContours(thresh_img, [cont], -1, (0,0,255), 5)
                x = 0
                y = 0
                for point in approx:
                    x += point[0][0] / w
                    y += point[0][1] / h
    return blur

cv2.namedWindow("Trackbars")
cv2.createTrackbar("L-H","Trackbars",0,255,nothing)
cv2.createTrackbar("L-S","Trackbars",0,255,nothing)
cv2.createTrackbar("L-V","Trackbars",0,255,nothing)
cv2.createTrackbar("U-H","Trackbars",255,255,nothing)
cv2.createTrackbar("U-S","Trackbars",255,255,nothing)
cv2.createTrackbar("U-V","Trackbars",255,255,nothing)

while(True):
  time.sleep(0.04)
  # Capture frame-by-frame
  ret, frame = cap.read()
  l_h=cv2.getTrackbarPos("L-H","Trackbars")
  l_s=cv2.getTrackbarPos("L-S","Trackbars")
  l_v=cv2.getTrackbarPos("L-V","Trackbars")
  u_h=cv2.getTrackbarPos("U-H","Trackbars")
  u_s=cv2.getTrackbarPos("U-S","Trackbars")
  u_v=cv2.getTrackbarPos("U-V","Trackbars")
  
  # Our operations on the frame come here
  frame=detectocta(frame,l_h,l_s,l_v,u_h,u_s,u_v)
  #contours =  cv2.findContours(thresh_img,cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)[-2]
  #x1,y1=0,0
  #for c in contours:
  #  approx = cv2.approxPolyDP(c, 0.01* cv2.arcLength(c, True), True)
  #  x1 += approx.ravel()[0]
  #  y1 += approx.ravel()[1]
  #cv2.drawContours(frame, contours, -1, (0,255,0), 3)
  #if len(contours)==0:
  #  pass
  #else:
  #  x1=x1/len(contours)
  #  y1=y1/len(contours)
  #frame=cv2.circle(frame,(int(x1)+10,int(y1)+10),10,(0,0,255),5)
  cv2.imshow('frame',frame)
  if cv2.waitKey(1) & 0xFF == ord('q'):
    break

# When everything done, release the capture
cap.release()
cv2.destroyAllWindows()
