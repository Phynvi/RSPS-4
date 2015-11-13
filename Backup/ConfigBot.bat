@echo off
color 22
Title Server Helper By AAA Mods
:start
echo ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo ::               DeepHaven Server Control                 ::
echo ::                          by                            ::
echo ::                       AAA Mods                         ::
echo ::                                                        ::
echo ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo.
echo For a test type t
echo.
echo For a ban type b
echo.
echo If you want to add a item to your item.cfg type i
echo.
echo If you want to add in drops type n
echo.
echo If you want to add in NPCS type s
echo.
echo If you want to add in cape type a
echo.
set /p c=Option:
if %c%==t goto test1
if %c%==b goto ban
if %c%==i goto itemcfg
if %c%==n goto name
if %c%==s goto npc
if %c%==a goto add
echo.
goto error
:error
cls
echo Invalid command. Please type in only commands from the command menu.
pause
cls
goto start
:cmd
:Star
Set /p CmdName=Command Name?
Set /p RUSure=Are You Sure To Give IT THe Name %CmdName%(y,n)?
If %RUSure%==n GOTO Star
If not Exist Commands MD Commands
Echo else if(command.equalsignorecase("%CmdName%")) >> ".\Commands\%CmdName% Command.txt"
Echo { >> ".\Commands\%CmdName% Command.txt"
set /p AY=addItem(y,n)?
If %AY%==Y Goto addItem
If %AY%==y Goto addItem
If %AY%==n Goto endCode
If %AY%==Y Goto endCode

:error
cls
echo Invalid command. Please type in only commands from the command menu.
pause
goto menu


:ban
cls
set /p ban=Username?
echo %ban%>> ".\data\bannedusers.txt"
echo %ban% has been successfully banned
Pause
cls
goto start


:add
cls
set /p itemid1=Item Id1?
set /p itemid2=Item Id2?
set /p statid=Stat id?
set /p skillname=Skill Name?
echo if ((wearID == %itemid1% || wearID == %itemid2%) && playerLevel[%statid%] <= 98){ >> ".\cfg\skill.txt"
echo equipcape("%skillname%"); >> ".\cfg\skill.txt"
echo } >> ".\cfg\skill.txt"
echo %skillname% cape has been successfully added.
Pause
cls
goto start


:itemcfg
cls
set /p itemid=ItemId?
set /p itemprice=Item Price?
set /p itemname=Item Name?
set /p itemb=Strength Bonus?
echo item = %itemid%	%itemname%	none	%itemprice%	%itemprice%	%itemprice%	0	0	0	0	0	0	0	0	0	0	%itemb%	0 >> ".\cfg\item.cfg"
echo %itemname% has been successfully added.
Pause
cls
goto start

:npc
cls
set /p npcid=NPC ID?
set /p npcname=NPC NAME?
set /p level=Level?
set /p health=Health?
echo npc = %npcid%	%npcname%	%level%	%health% >> ".\cfg\npc.cfg"
echo %npcname% has been successfully added.
Pause
cls
goto start


:name
cls
set /p name=What do you want the name to be? 
set /p id=What do you want the id to be?
echo public static int %name%[] = {item}; >> ".\drops.txt"
echo  public static int random%name%() >> ".\drops.txt"
echo  { >> ".\drops.txt"
echo  	return %name%[(int)(Math.random()*%name%.length)]; >> ".\drops.txt"
echo  } >> ".\drops.txt"
echo if(npcs[NPCID].npcType == %id%) { >> ".\drops2.txt"
echo ItemHandler.addItem(Item2.random%name%(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false); >> ".\drops2.txt"
echo } >> ".\drops2.txt"
echo %name% has been successfully added.
Pause
cls
goto start





:test1
cls
echo THIS IS A TEST
color 0a
echo What you are now reading is a test, configured by AAA Mods
echo from looking around in this bat file! =)
Pause
cls
goto start














--------------------------------------------------------------------------------