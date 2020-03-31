package BL;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Haendler
{
  private LinkedList<Bullet> bulletList;
  private Player player;
  private Canvas canvas;
  private LinkedList<Asteroid> asteroidList;
  private int shoot, forward, left, right, pause;

  public Haendler()
  {
    bulletList = new LinkedList();
    asteroidList = new LinkedList();
    controlls();
  }

  private void controlls()
  {
    shoot = 32;
    forward = 38;
    left = 37;
    right = 39;
    pause = 27;
  }

  public void addBullet(double x, double y, double direction)
  {
    bulletList.add(new Bullet(direction, x, y));
    bulletList.get(bulletList.size() - 1).setCanvas(canvas);
  }

  public void addAsteroid(int counter, int numberOfAsteroids)
  {
    if (asteroidList.size() < numberOfAsteroids && counter % 25 == 0)
    {
      int a = (int) (Math.random() * 75) + 1;
      if (counter % 100 == 0)
      {
        asteroidList.add(new Asteroid(canvas.getWidth() / 2.0 * -1, canvas.getHeight() / 2.0, a, Size.BIG));
      } else if (counter % 100 == 25)
      {
        asteroidList.add(new Asteroid(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0, a + 105, Size.BIG));
      } else if (counter % 100 == 75)
      {
        asteroidList.add(new Asteroid(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0 * -1, a + 195, Size.BIG));
      } else if (counter % 100 == 50)
      {
        asteroidList.add(new Asteroid(canvas.getWidth() / 2.0 * -1, canvas.getHeight() / 2.0 * -1, a + 285, Size.BIG));
      }
      asteroidList.get(asteroidList.size() - 1).setCanvas(canvas);
    }
  }

  public void moveBullet()
  {
    int i = 0;
    while (i < bulletList.size())
    {
      Bullet b = bulletList.get(i);
      b.move();
      if (b.getX() + canvas.getWidth() / 2 > canvas.getWidth() || b.getX() + canvas.getWidth() / 2 < 0 || canvas.getHeight() / 2 - b.getY() < 0 || canvas.getHeight() / 2 - b.getY() > canvas.getWidth())
      {
        bulletList.remove(i);
        i--;
      }
      i++;
    }
  }

  public void moveAsteroid()
  {
    int i = 0;
    double w2, h2;
    w2 = canvas.getWidth() / 2.0;
    h2 = canvas.getHeight() / 2.0;
    while (i < asteroidList.size())
    {
      Asteroid ast = asteroidList.get(i);
      ast.move();
      double x = ast.getX(), y = ast.getY();
      //ast.getX() + canvas.getWidth() / 2 > canvas.getWidth() || ast.getX() + canvas.getWidth() / 2 < 0 || canvas.getHeight() / 2 - ast.getY() < 0 || canvas.getHeight() / 2 - ast.getY() > canvas.getWidth()
      if (x + w2 >= canvas.getWidth())
      {
        ast.setX(w2 * -1);
      } else if (x + w2 <= 0)
      {
        ast.setX(w2);
      } else if (h2 - y >= canvas.getHeight())
      {
        ast.setY(h2);
      } else if (h2 - y <= 0)
      {
        ast.setY(h2 * -1);
      }
      i++;
    }
  }


  public void bulletHitDetector()
  {
    int i = 0, j, rad;
    double x, y;
    double c;
    double a, b;
    Bullet bul;
    Asteroid ast;

    while (i < bulletList.size())
    {
      bul = bulletList.get(i);
      x = Math.cos(90 - (bul.getDirection() % 90));
      if (bul.getDirection() % 360 > 180 && bul.getDirection() % 360 < 360)
      {
        x *= -1;
      }
      y = Math.sin(90 - (bul.getDirection() % 90));
      if (bul.getDirection() % 360 > 90 && bul.getDirection() < 270)
      {
        y *= -1;
      }
      x += bul.getX();
      y += bul.getY();
      j = 0;
      while (j < asteroidList.size() && i >= 0)
      {
        ast = asteroidList.get(j);

        a = x - ast.getX();
        b = y - ast.getY();
        c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        rad = ast.getRad();
        if (c <= rad)
        {
          bulletList.remove(i);
          i--;
          if (ast.getSize().equals(Size.BIG))
          {
            asteroidList.add(new Asteroid(ast.getX() + rad, ast.getY() + rad, ast.getDirection() + 90, Size.MEDIUM));
            asteroidList.get(asteroidList.size() - 1).setCanvas(canvas);
            asteroidList.add(new Asteroid(ast.getX() - rad, ast.getY() - rad, ast.getDirection() - 90, Size.MEDIUM));
            asteroidList.get(asteroidList.size() - 1).setCanvas(canvas);

          } else if (ast.getSize().equals(Size.MEDIUM))
          {
            asteroidList.add(new Asteroid(ast.getX() + rad, ast.getY() + rad, ast.getDirection() + 90, Size.SMALL));
            asteroidList.get(asteroidList.size() - 1).setCanvas(canvas);
            asteroidList.add(new Asteroid(ast.getX() - rad, ast.getY() - rad, ast.getDirection() - 90, Size.SMALL));
            asteroidList.get(asteroidList.size() - 1).setCanvas(canvas);
          }
          asteroidList.remove(j);
          j--;
        }
        j++;
      }
      i++;
    }
  }

  public boolean playerHitDetector()
  {
    double x, y, a, b;
    double[] c = new double[3];
    int i;

    for (Asteroid ast : asteroidList)
    {
      x = ast.getX();
      y = ast.getY();
      a = player.getCanon().getX() - x;
      b = player.getCanon().getY() - y;
      c[0] = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
      a = player.getLeftFoot().getX() - x;
      b = player.getLeftFoot().getY() - y;
      c[1] = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
      a = player.getRightFoot().getX() - x;
      b = player.getRightFoot().getY() - y;
      c[2] = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
      for (i = 0; i < c.length; i++)
      {
        if (c[i] < ast.getRad())
        {
          return true;
        }
      }
    }

    return false;
  }


  public LinkedList<Bullet> getBulletList()
  {
    return bulletList;
  }

  public void setBulletList(LinkedList<Bullet> bulletList)
  {
    this.bulletList = bulletList;
  }

  public Player getPlayer()
  {
    return player;
  }

  public void setPlayer(Player player)
  {
    this.player = player;
  }

  public Canvas getCanvas()
  {
    return canvas;
  }

  public void setCanvas(Canvas canvas)
  {
    this.canvas = canvas;
  }

  public LinkedList<Asteroid> getAsteroidList()
  {
    return asteroidList;
  }

  public void setAsteroidList(LinkedList<Asteroid> asteroidList)
  {
    this.asteroidList = asteroidList;
  }

  public void asteroidCollision()
  {
    int i = 0, j, rad;
    double a, b, c;
    Asteroid ast, ast2;
    while (i < asteroidList.size())
    {
      ast = asteroidList.get(i);
      j = i + 1;
      while (j < asteroidList.size())
      {
        ast2 = asteroidList.get(j);
        a = ast.getX() - ast2.getX();
        b = ast.getY() - ast2.getY();
        c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        rad = ast.getRad() + ast2.getRad();
        if (c <= rad)
        {
          ast.rotate(180);
          ast2.rotate(180);
        }
        j++;
      }
      i++;
    }
  }

  public boolean playerMovement(Set<Integer> pressed)
  {
    for (Integer pres : pressed)
    {
      System.out.println(""+pres);
      if (pres == left)
      {
        player.rotate(-5);
      } else if (pres == right)
      {
        player.rotate(5);
      } else if (pres == forward)
      {
        player.move(5);
      } else if (pres == shoot && bulletList.size() <=5)
      {
        addBullet(player.getCanon().getX(), player.getCanon().getY(), player.getDirection());
      } else if (pres == pause)
      {
        return true;
      }
    }
    return false;
  }

  public int getShoot()
  {
    return shoot;
  }

  public void setShoot(int shoot)
  {
    this.shoot = shoot;
  }

  public int getForward()
  {
    return forward;
  }

  public void setForward(int forward)
  {
    this.forward = forward;
  }

  public int getLeft()
  {
    return left;
  }

  public void setLeft(int left)
  {
    this.left = left;
  }

  public int getRight()
  {
    return right;
  }

  public void setRight(int right)
  {
    this.right = right;
  }

  public int getPause()
  {
    return pause;
  }

  public void setPause(int pause)
  {
    this.pause = pause;
  }
}
