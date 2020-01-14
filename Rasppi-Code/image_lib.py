import numpy as np
import cv2
import sys

def color_framing(frames,l_h,l_s,l_v,u_h,u_s,u_v):
    frame=frames
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    lower=np.array([l_h,l_s,l_v])
    upper=np.array([u_h,u_s,u_v])
    mask=cv2.inRange(hsv,lower,upper)
    frame=cv2.bitwise_and(frame,frame,mask=mask)
    kernel = np.ones((5,5), np.uint8) 
    frame=cv2.erode(frame, kernel, iterations=1)
    return frame
def detectcircle(frames,l_h,l_s,l_v,u_h,u_s,u_v,d_p,m_d):
    frame=frames
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    lower_green = np.array([l_h,l_s,l_v])
    upper_green= np.array([u_h,u_s,u_v])
    greenmask=cv2.inRange(hsv,lower_green,upper_green)
    frame=cv2.bitwise_and(frame,frame,mask=greenmask)
    kernel = np.ones((5,5), np.uint8) 
    frame=cv2.erode(frame, kernel, iterations=1)
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    blur = cv2.GaussianBlur(gray,(5,5),0)
    ret, thresh_img = cv2.threshold(blur,91,255,cv2.THRESH_BINARY)
    circles = cv2.HoughCircles(thresh_img, cv2.HOUGH_GRADIENT, d_p, m_d)
    if circles is not None:
        circles = np.round(circles[0, :]).astype("int")
        for (x, y, r) in circles:
            cv2.circle(frame, (x, y), r, (0, 255, 0), 4)
            cv2.rectangle(frame, (x - 5, y - 5), (x + 5, y + 5), (0, 128, 255), -1)
    return frame
    