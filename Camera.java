import static org.lwjgl.opengl.GL11.*;

public class Camera
{

	private Transform transform;
	
	private Vector3 ghost1;
	private Vector3 ghost2;
	private Vector3 ghost3;
	private Vector3 ghost4;
	
	public Camera(Vector3f pos)
	{	
		this.transform = new Transform(pos.invert(), new Vector3f(0.5f, 2.5f, 0.5f), new Vector3f());
		ghost1 = new Vector3();
		ghost2 = new Vector3();
		ghost3 = new Vector3();
		ghost4 = new Vector3();
	}
	
	public void look()
	{
		View.refresh3D();
		
		glRotatef(this.transform.getRotation().getX(), 1, 0, 0);
		glRotatef(this.transform.getRotation().getY(), 0, 1, 0);
		glRotatef(this.transform.getRotation().getZ(), 0, 0, 1);
		
		glTranslatef(this.transform.getPosition().getX(), this.transform.getPosition().getY(), this.transform.getPosition().getZ());
	}
	
	public void move(float ang, float amt)
	{
		
		float f = 0;
		
		if(ang == -90)
		{
			f = -1;
		}
		
		if(ang == 90)
		{
			f = 1;
		}
		
		Vector3f addid = new Vector3f();
		
		addid.setX(amt * (float)Math.cos(Math.toRadians(this.transform.getRotation().getY() + ang)));
		//addid.setY(amt * (float)Math.sin(Math.toRadians(this.transform.getRotation().getX())) * f);
		addid.setZ(amt * (float)Math.sin(Math.toRadians(this.transform.getRotation().getY() + ang)));
		
		Vector3f collision = checkCollision(this.transform.getRawPosition(), this.transform.getRawPosition().add(addid), addid);
		
		this.transform.setPosition(this.transform.getRawPosition().add(collision));
		
	}
	
	public void rotate(Vector3f rotate)
	{
	
		rotate = rotate.mul(-Input.mouseSensibillity, Input.mouseSensibillity, 1);
		
		this.transform.rotate(rotate);
		
		if(this.transform.getRotation().getX() > 90)
		{
			this.transform.getRotation().setX(90);
		}
		
		if(this.transform.getRotation().getX() < -90)
		{
			this.transform.getRotation().setX(-90);
		}
		
	}
	
	public void rotate(float angX, float angY, float angZ)
	{
		rotate(new Vector3f(angX,angY,angZ));
	}
	
	public Vector3f getPos()
	{
		return this.transform.getPosition();
	}
	
	public Transform getTransform()
	{
		return this.transform;
	}
	
}
